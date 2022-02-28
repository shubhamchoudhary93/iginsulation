package com.shubham.iginsulation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.shubham.iginsulation.BackupRestore.backup
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
                        val tables = listOf(
                            "customer",
                            "sale",
                            "sale_details",
                            "stock",
                            "shop_stock",
                            "shop_stock_transaction",
                            "transaction",
                            "rate"
                        )

                        for (table in tables)
                            backup(context, table)
                    }
                    .show()
            }

        }
    }
}