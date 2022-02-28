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
import com.shubham.iginsulation.databinding.FragmentVarnishEditBinding
import java.text.SimpleDateFormat
import java.util.*

class VarnishEditFragment : Fragment() {

    private lateinit var binding: FragmentVarnishEditBinding
    private lateinit var rateDatabase: RateDatabaseDao

    private var id = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_varnish_edit, container, false
        )

        rateDatabase = RateDatabase.getInstance(requireContext()).rateDatabaseDao

        val args = VarnishEditFragmentArgs.fromBundle(requireArguments())
        id = args.id

        fetchRate(id)

        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.modify.setOnClickListener {

            val name = binding.name.text.toString()
            var billCostPrice = binding.billCostPrice.text.toString()
            if (billCostPrice.toIntOrNull() == null) {
                Toast.makeText(context, "Bill Cost Price should be numeric", Toast.LENGTH_SHORT)
                    .show()
                billCostPrice = ""
            }
            var costPrice = binding.costPrice.text.toString()
            if (costPrice.toIntOrNull() == null) {
                Toast.makeText(context, "Cost Price should be numeric", Toast.LENGTH_SHORT).show()
                costPrice = ""
            }
            var billSellPrice = binding.billSellPrice.text.toString()
            if (billSellPrice.toIntOrNull() == null) {
                Toast.makeText(context, "Bill Sell Price should be numeric", Toast.LENGTH_SHORT)
                    .show()
                billSellPrice = ""
            }
            var sellPrice = binding.sellPrice.text.toString()
            if (sellPrice.toIntOrNull() == null) {
                Toast.makeText(context, "Sell Price should be numeric", Toast.LENGTH_SHORT).show()
                sellPrice = ""
            }

            val seller = binding.seller.text.toString()

            modifyRate(
                name,
                if (billCostPrice == "")
                    0F
                else
                    billCostPrice.toFloat(),
                if (costPrice == "")
                    0F
                else
                    costPrice.toFloat(),
                if (billSellPrice == "")
                    0F
                else
                    billSellPrice.toFloat(),
                if (sellPrice == "")
                    0F
                else
                    sellPrice.toFloat(),
                seller,
                SimpleDateFormat("d/M/yyyy", Locale.ENGLISH).format(Date())
            )
        }
    }

    private fun modifyRate(
        name: String,
        billCostPrice: Float,
        costPrice: Float,
        billSellPrice: Float,
        sellPrice: Float,
        seller: String,
        date: String
    ) {
        val rate = Rate()
        rate.id = id
        rate.name = name
        rate.billCostPrice = billCostPrice
        rate.costPrice = costPrice
        rate.billSellPrice = billSellPrice
        rate.sellPrice = sellPrice
        rate.seller = seller
        rate.date = date

        try {
            rateDatabase.update(rate)
            BackupRestore.backup(context, "rate")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        view?.findNavController()?.navigate(
            VarnishEditFragmentDirections.actionVarnishEditFragmentToVarnishFragment()
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
        binding.billCostPrice.setText(rate.billCostPrice.toInt().toString())
        binding.costPrice.setText(rate.costPrice.toInt().toString())
        binding.billSellPrice.setText(rate.billSellPrice.toInt().toString())
        binding.sellPrice.setText(rate.sellPrice.toInt().toString())
        binding.seller.setText(rate.seller)
    }
}