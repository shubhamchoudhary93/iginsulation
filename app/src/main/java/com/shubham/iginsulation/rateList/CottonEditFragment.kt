package com.shubham.iginsulation.rateList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.BackupRestore
import com.shubham.iginsulation.R
import com.shubham.iginsulation.database.rate.Rate
import com.shubham.iginsulation.database.rate.RateDatabase
import com.shubham.iginsulation.database.rate.RateDatabaseDao
import com.shubham.iginsulation.databinding.FragmentCottonEditBinding

import java.text.SimpleDateFormat
import java.util.*

class CottonEditFragment : Fragment() {

    private lateinit var binding: FragmentCottonEditBinding
    private lateinit var rateDatabase: RateDatabaseDao

    private var id = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_cotton_edit, container, false
        )

        rateDatabase = RateDatabase.getInstance(requireContext()).rateDatabaseDao

        val args =  CottonEditFragmentArgs.fromBundle(requireArguments())
        id = args.id

        fetchRate(id)

        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.modify.setOnClickListener {

            val name = binding.name.text.toString()
            var costPrice = binding.costPrice.text.toString()
            if (costPrice.toFloatOrNull() == null) {
                Toast.makeText(context, "Cost Price should be numeric", Toast.LENGTH_SHORT).show()
                costPrice = ""
            }
            var sellPrice = binding.sellPrice.text.toString()
            if (sellPrice.toFloatOrNull() == null) {
                Toast.makeText(context, "Sell Price should be numeric", Toast.LENGTH_SHORT).show()
                sellPrice = ""
            }

            modifyRate(
                name,
                if (costPrice == "")
                    0F
                else
                    costPrice.toFloat(),
                if (sellPrice == "")
                    0F
                else
                    sellPrice.toFloat(),
                SimpleDateFormat("d/M/yyyy", Locale.ENGLISH).format(Date())
            )
        }
    }

    private fun modifyRate(
        name: String,
        costPrice: Float,
        sellPrice: Float,
        date: String
    ) {
        val rate = Rate()
        rate.id = id
        rate.name = name
        rate.billCostPrice = 0F
        rate.costPrice = costPrice
        rate.billSellPrice = 0F
        rate.sellPrice = sellPrice
        rate.seller = ""
        rate.date = date

        try {
            rateDatabase.update(rate)
            BackupRestore.backup(context, "rate")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        view?.findNavController()?.navigate(
             CottonEditFragmentDirections.actionCottonEditFragmentToCottonFragment()
        )
    }

    private fun fetchRate(id: Long) {
        val rate = if (id == 0L) {
            try {
                rateDatabase.getLastRate()
            } catch (e: Exception) {
                e.printStackTrace()
                Rate()
            }
        } else {
            try {
                rateDatabase.get(id)!!
            } catch (e: Exception) {
                e.printStackTrace()
                Rate()
            }
        }
        setRateData(rate)
    }

    private fun setRateData(rate: Rate) {
        binding.name.text = rate.name
        binding.costPrice.setText(rate.costPrice.toString())
        binding.sellPrice.setText(rate.sellPrice.toString())
    }
}