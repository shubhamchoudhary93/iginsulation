package com.shubham.iginsulation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.database.customer.CustomerDatabase
import com.shubham.iginsulation.database.sale.SaleDatabase
import com.shubham.iginsulation.database.saledetails.SaleDetailsDatabase
import com.shubham.iginsulation.database.shopStockTransaction.ShopStockTransactionDatabase
import com.shubham.iginsulation.database.shopstock.ShopStockDatabase
import com.shubham.iginsulation.database.stock.StockDatabase
import com.shubham.iginsulation.database.transaction.TransactionDatabase
import com.shubham.iginsulation.databinding.FragmentBillingBinding
import com.shubham.iginsulation.databinding.FragmentSettingsBinding

class SettingsFragment: Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_settings, container, false
        )

        setListeners()

        return binding.root
    }

    private fun setListeners() {

        binding.buttonDummy.setOnClickListener {

            val customerDatabase =
                CustomerDatabase.getInstance(requireContext()).customerDatabaseDao
            val saleDatabase = SaleDatabase.getInstance(requireContext()).saleDatabaseDao
            val saleDetailsDatabase =
                SaleDetailsDatabase.getInstance(requireContext()).saleDetailsDatabaseDao
            val shopStockDatabase =
                ShopStockDatabase.getInstance(requireContext()).shopStockDatabaseDao
            val shopStockTransactionDatabase =
                ShopStockTransactionDatabase.getInstance(requireContext()).shopStockTransactionDatabaseDao
            val stockDatabase =
                StockDatabase.getInstance(requireContext()).stockDatabaseDao
            val transactionDatabase =
                TransactionDatabase.getInstance(requireContext()).transactionDatabaseDao

            DummyData.populate(customerDatabase, stockDatabase, shopStockDatabase, saleDatabase, saleDetailsDatabase, shopStockTransactionDatabase, transactionDatabase)
            binding.notifications.text = "dummy data created"
        }

        binding.buttonBackup.setOnClickListener {
            BackupRestore.backup(requireContext())
            binding.notifications.text = "data backup done"
        }

        binding.buttonRestore.setOnClickListener {
            BackupRestore.restore(requireContext())
            binding.notifications.text = "data restore done"
        }
    }
}