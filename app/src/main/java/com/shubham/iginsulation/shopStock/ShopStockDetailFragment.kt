package com.shubham.iginsulation.shopStock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.R
import com.shubham.iginsulation.database.shopstock.ShopStock
import com.shubham.iginsulation.database.shopstock.ShopStockDatabase
import com.shubham.iginsulation.database.shopstock.ShopStockDatabaseDao
import com.shubham.iginsulation.databinding.FragmentShopStockDetailBinding

class ShopStockDetailFragment : Fragment() {

    private lateinit var binding: FragmentShopStockDetailBinding
    private lateinit var shopStockDatabase: ShopStockDatabaseDao

    private var id = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shop_stock_detail, container, false
        )

        shopStockDatabase = ShopStockDatabase.getInstance(requireContext()).shopStockDatabaseDao

        val args = ShopStockDetailFragmentArgs.fromBundle(requireArguments())
        id = args.id

        fetchShopStock(id)

        binding.shopStockDetailDelete.setOnClickListener {
            shopStockDatabase.delete(id)
            view?.findNavController()?.navigate(
                ShopStockDetailFragmentDirections.actionShopStockDetailFragmentToShopStockListFragment()
            )
        }

        binding.shopStockDetailModify.setOnClickListener {
            view?.findNavController()?.navigate(
                ShopStockDetailFragmentDirections.actionShopStockDetailFragmentToShopStockModifyFragment(id)
            )
        }

        return binding.root
    }

    private fun fetchShopStock(id: Long) {
        val shopStock = if (id == 0L) {
            try {
                shopStockDatabase.getLastShopStock()
            } catch (e: Exception) {
                e.printStackTrace()
                ShopStock()
            }
        } else {
            try {
                shopStockDatabase.get(id)!!
            } catch (e: Exception) {
                e.printStackTrace()
                ShopStock()
            }
        }
        setShopStockData(shopStock)
    }

    private fun setShopStockData(shopStock: ShopStock) {
        binding.shopStockDetailName.text = shopStock.name
        binding.shopStockDetailCategory.text = shopStock.category
        binding.shopStockDetailSubCategory.text = shopStock.subCategory
        binding.shopStockDetailQuantity.text = shopStock.quantity.toString()
        binding.shopStockDetailMinQuantity.text = shopStock.minQuantity.toString()
        binding.shopStockDetailDefaultReduce.text = shopStock.defaultReduce.toString()
        binding.shopStockDetailRate.text = shopStock.rate.toString()
        binding.shopStockDetailSeller.text = shopStock.seller

    }
}