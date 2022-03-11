package com.shubham.iginsulation.shopStock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.BackupRestore
import com.shubham.iginsulation.R
import com.shubham.iginsulation.database.shopStockTransaction.ShopStockTransaction
import com.shubham.iginsulation.database.shopStockTransaction.ShopStockTransactionDatabase
import com.shubham.iginsulation.database.shopStockTransaction.ShopStockTransactionDatabaseDao
import com.shubham.iginsulation.database.shopstock.ShopStock
import com.shubham.iginsulation.database.shopstock.ShopStockDatabase
import com.shubham.iginsulation.database.shopstock.ShopStockDatabaseDao
import com.shubham.iginsulation.databinding.FragmentShopStockDetailBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ShopStockDetailFragment : Fragment() {

    private lateinit var binding: FragmentShopStockDetailBinding
    private lateinit var shopStockDatabase: ShopStockDatabaseDao
    private lateinit var shopStockTransactionDatabase: ShopStockTransactionDatabaseDao

    private var id = 0L
    private var name = ""
    private var date = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shop_stock_detail, container, false
        )

        shopStockDatabase = ShopStockDatabase.getInstance(requireContext()).shopStockDatabaseDao
        shopStockTransactionDatabase =
            ShopStockTransactionDatabase.getInstance(requireContext()).shopStockTransactionDatabaseDao

        val args = ShopStockDetailFragmentArgs.fromBundle(requireArguments())
        id = args.id

        fetchShopStock(id)

        binding.delete.setOnClickListener {
            shopStockDatabase.delete(id)
            BackupRestore.backup(context, "shop_stock")
            view?.findNavController()?.navigate(
                ShopStockDetailFragmentDirections.actionShopStockDetailFragmentToShopStockListFragment()
            )
        }

        binding.modify.setOnClickListener {
            view?.findNavController()?.navigate(
                ShopStockDetailFragmentDirections.actionShopStockDetailFragmentToShopStockModifyFragment(
                    id
                )
            )
        }

        fetchAdaptor()
        return binding.root
    }

    private fun fetchShopStock(id: Long) {
        if (id == 0L) {
            view?.findNavController()?.navigate(
                ShopStockDetailFragmentDirections.actionShopStockDetailFragmentToShopStockListFragment()
            )
        } else {
            try {
                setShopStockData(shopStockDatabase.get(id)!!)
            } catch (e: Exception) {
                view?.findNavController()?.navigate(
                    ShopStockDetailFragmentDirections.actionShopStockDetailFragmentToShopStockListFragment()
                )
            }
        }
    }

    private fun setShopStockData(shopStock: ShopStock) {
        if (shopStock.name != "") {
            binding.topAppBar.title = shopStock.name
            name = shopStock.name
        }
        if (shopStock.category != "")
            binding.categoryValue.text = shopStock.category
        else {
            binding.categoryText.visibility = View.GONE
            binding.categoryValue.visibility = View.GONE
        }
        if (shopStock.subCategory != "")
            binding.subCategoryValue.text = shopStock.subCategory
        else {
            binding.subCategoryText.visibility = View.GONE
            binding.subCategoryValue.visibility = View.GONE
        }
        binding.quantityValue.text = shopStock.quantity.toString()
        binding.closingBalance.text = shopStock.quantity.toString()

        if (shopStock.minQuantity != 0)
            binding.minQuantityValue.text = shopStock.minQuantity.toString()
        else {
            binding.minQuantityText.visibility = View.GONE
            binding.minQuantityValue.visibility = View.GONE
        }
        if (shopStock.defaultReduce != 0)
            binding.defaultReduceValue.text = shopStock.defaultReduce.toString()
        else {
            binding.defaultReduceText.visibility = View.GONE
            binding.defaultReduceValue.visibility = View.GONE
        }
        if (shopStock.rate != 0F)
            binding.rateValue.text = shopStock.rate.toString()
        else {
            binding.rateText.visibility = View.GONE
            binding.rateValue.visibility = View.GONE
        }
        if (shopStock.seller != "")
            binding.sellerValue.text = shopStock.seller
        else {
            binding.sellerText.visibility = View.GONE
            binding.sellerValue.visibility = View.GONE
        }

        binding.dateValue.text = shopStock.date
        binding.openingBalance.text = shopStock.opening.toString()
        binding.date.text = shopStock.date
        date = shopStock.date
    }

    private fun fetchAdaptor() {
        val list = shopStockTransactionDatabase.getListByExactName(name)

        val newList = mutableListOf<ShopStockTransaction>()
        val startDate = LocalDate.parse(
            date,
            DateTimeFormatter.ofPattern("d/M/yyyy")
        )

        for (l in list) {
            if (startDate.minusDays(1).isBefore(
                    LocalDate.parse(
                        l.date,
                        DateTimeFormatter.ofPattern("d/M/yyyy")
                    )
                )
            ) {
                newList.add(l)
            }
        }
        val adapter = ShopStockDetailAdaptor(ShopStockDetailAdaptor.ShopStockTransactionListener {
//            view?.findNavController()?.navigate(
//                ShopStockDetailFragmentDirections.act(
//                    it
//                )
//            )
        })

        binding.list.adapter = adapter
        adapter.submitList(newList)
    }
}