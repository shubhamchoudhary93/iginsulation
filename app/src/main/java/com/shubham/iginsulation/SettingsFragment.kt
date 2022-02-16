package com.shubham.iginsulation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.shubham.iginsulation.database.rate.Rate
import com.shubham.iginsulation.database.rate.RateDatabase
import com.shubham.iginsulation.database.rate.RateDatabaseDao
import com.shubham.iginsulation.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var rateDatabase: RateDatabaseDao

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

        binding.createRateListDatabase.setOnClickListener {
            rateDatabase = RateDatabase.getInstance(requireContext()).rateDatabaseDao

            rateDatabase.clear()
            val data = arrayOf(
                Rate(0, "Cotton 1"),
                Rate(0, "Cotton 2"),
                Rate(0, "Cotton 3"),
                Rate(0, "Cotton 4"),
                Rate(0, "Cotton 5"),
                Rate(0, "Cotton 6"),
                Rate(0, "Cotton 8"),
                Rate(0, "Cotton 10"),
                Rate(0, "Cotton shubham 2"),
                Rate(0, "Cotton ganga 2")
            )

            for (i in 0..9) {
                rateDatabase.insert(data[i])
            }
        }
    }
}