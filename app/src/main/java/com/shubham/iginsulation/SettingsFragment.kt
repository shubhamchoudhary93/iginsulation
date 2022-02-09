package com.shubham.iginsulation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.shubham.iginsulation.database.customer.CustomerDatabase
import com.shubham.iginsulation.database.sale.SaleDatabase
import com.shubham.iginsulation.database.saledetails.SaleDetailsDatabase
import com.shubham.iginsulation.database.shopStockTransaction.ShopStockTransactionDatabase
import com.shubham.iginsulation.database.shopstock.ShopStockDatabase
import com.shubham.iginsulation.database.stock.StockDatabase
import com.shubham.iginsulation.database.transaction.TransactionDatabase
import com.shubham.iginsulation.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

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

        binding.buttonBackup.setOnClickListener {
            context?.let { it1 ->
                MaterialAlertDialogBuilder(it1)
                    .setTitle("Confirm")
                    .setMessage(
                        "This option will backup your mobile data to server.\n" +
                                "You sure about this?"
                    )
                    .setNegativeButton("No") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .setPositiveButton("Yes") { _, _ ->
                        BackupRestore.backup(requireContext(), binding)
                    }
                    .show()
            }

        }

        binding.buttonRestore.setOnClickListener {
            context?.let { it1 ->
                MaterialAlertDialogBuilder(it1)
                    .setTitle("Confirm")
                    .setMessage(
                        "This option will restore server data to mobile.\n" +
                                "You sure about this?"
                    )
                    .setNegativeButton("No") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .setPositiveButton("Yes") { _, _ ->
                        BackupRestore.restore(requireContext(), binding)
                    }
                    .show()
            }
        }
    }
}