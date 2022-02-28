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
import com.shubham.iginsulation.database.shopstock.ShopStockDatabase
import com.shubham.iginsulation.database.shopstock.ShopStockDatabaseDao
import com.shubham.iginsulation.databinding.FragmentShopStockTransactionDetailBinding

class ShopStockTransactionDetailFragment : Fragment() {

    private lateinit var binding: FragmentShopStockTransactionDetailBinding
    private lateinit var shopStockTransactionDatabase: ShopStockTransactionDatabaseDao
    private lateinit var shopStockDatabase: ShopStockDatabaseDao

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

        shopStockDatabase =
            ShopStockDatabase.getInstance(requireContext()).shopStockDatabaseDao

        val args = ShopStockTransactionDetailFragmentArgs.fromBundle(requireArguments())
        id = args.id

        fetchShopStockTransaction(id)

        binding.delete.setOnClickListener {
            shopStockTransactionDatabase.delete(id)
            val oldQuantity =
                shopStockDatabase.getShopStockQuantity(shopStockTransaction.stock)
            val newQuantity =
                if (shopStockTransaction.add) oldQuantity + shopStockTransaction.quantity
                else oldQuantity - shopStockTransaction.quantity
            shopStockDatabase.setShopStockQuantity(
                newQuantity,
                shopStockTransaction.stock
            )

            BackupRestore.backup(context, "shop_stock")
            BackupRestore.backup(context, "shop_stock_transaction")
            view?.findNavController()?.navigate(
                ShopStockTransactionDetailFragmentDirections.actionShopStockTransactionDetailFragmentToShopStockTransactionListFragment()
            )
        }

        binding.modify.setOnClickListener {
            view?.findNavController()?.navigate(
                ShopStockTransactionDetailFragmentDirections.actionShopStockTransactionDetailFragmentToShopStockTransactionModifyFragment(
                    id
                )
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
        if (shopStockTransaction.stock != "")
            binding.stockValue.text = shopStockTransaction.stock
        else {
            binding.stockValue.visibility = View.GONE
            binding.stockText.visibility = View.GONE
        }
        if (shopStockTransaction.quantity != 0)
            binding.quantityValue.text = shopStockTransaction.quantity.toString()
        else {
            binding.quantityText.visibility = View.GONE
            binding.quantityValue.visibility = View.GONE
        }
        if (shopStockTransaction.date != "")
            binding.dateValue.text = shopStockTransaction.date
        else {
            binding.dateValue.visibility = View.GONE
            binding.dateText.visibility = View.GONE
        }
        if (shopStockTransaction.detail != "")
            binding.detailValue.text = shopStockTransaction.detail
        else {
            binding.detailValue.visibility = View.GONE
            binding.detailText.visibility = View.GONE
        }
    }
}