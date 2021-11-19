package com.shubham.iginsulation.shopStock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.R
import com.shubham.iginsulation.database.customer.CustomerDatabase
import com.shubham.iginsulation.database.customer.CustomerDatabaseDao
import com.shubham.iginsulation.database.shopStockTransaction.ShopStockTransaction
import com.shubham.iginsulation.database.shopStockTransaction.ShopStockTransactionDatabase
import com.shubham.iginsulation.database.shopStockTransaction.ShopStockTransactionDatabaseDao
import com.shubham.iginsulation.databinding.FragmentShopStockTransactionDetailBinding

class ShopStockTransactionDetailFragment : Fragment() {

    private lateinit var binding: FragmentShopStockTransactionDetailBinding
    private lateinit var shopStockTransactionDatabase: ShopStockTransactionDatabaseDao
    private lateinit var customerDatabase: CustomerDatabaseDao

    private var id = 0L
    private var shopStockTransaction = ShopStockTransaction()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shop_stock_transaction_detail, container, false
        )

        shopStockTransactionDatabase =
            ShopStockTransactionDatabase.getInstance(requireContext()).shopStockTransactionDatabaseDao

        customerDatabase =
            CustomerDatabase.getInstance(requireContext()).customerDatabaseDao

        val args = ShopStockTransactionDetailFragmentArgs.fromBundle(requireArguments())
        id = args.id

        fetchShopStockTransaction(id)

        binding.shopStockTransactionDetailDelete.setOnClickListener {
            shopStockTransactionDatabase.delete(id)
            val oldCurrentBalance =
                customerDatabase.getCustomerCurrentBalance(shopStockTransaction.stock)
            val newBalanceO =
                if (shopStockTransaction.add) oldCurrentBalance + shopStockTransaction.quantity
                else oldCurrentBalance - shopStockTransaction.quantity
            customerDatabase.setCustomerCurrentBalance(
                newBalanceO,
                shopStockTransaction.stock
            )
            view?.findNavController()?.navigate(
                ShopStockTransactionDetailFragmentDirections.actionShopStockTransactionDetailFragmentToShopStockTransactionListFragment()
            )
        }

        binding.shopStockTransactionDetailModify.setOnClickListener {
            view?.findNavController()?.navigate(
                ShopStockTransactionDetailFragmentDirections.actionShopStockTransactionDetailFragmentToShopStockTransactionModifyFragment(id)
            )
        }

        return binding.root
    }

    private fun fetchShopStockTransaction(id: Long) {
        shopStockTransaction = if (id == 0L) {
            try {
                shopStockTransactionDatabase.getLastShopStockTransaction()
            } catch (e: Exception) {
                e.printStackTrace()
                ShopStockTransaction()
            }
        } else {
            try {
                shopStockTransactionDatabase.get(id)!!
            } catch (e: Exception) {
                e.printStackTrace()
                ShopStockTransaction()
            }
        }
        setShopStockTransactionData(shopStockTransaction)
    }

    private fun setShopStockTransactionData(shopStockTransaction: ShopStockTransaction) {
        binding.add.text = shopStockTransaction.add.toString()
        binding.shopStockTransactionDetailStock.text = shopStockTransaction.stock
        binding.shopStockTransactionDetailQuantity.text = shopStockTransaction.quantity.toString()
        binding.shopStockTransactionDetailDate.text = shopStockTransaction.date
        binding.shopStockTransactionDetailDetail.text = shopStockTransaction.detail
    }
}