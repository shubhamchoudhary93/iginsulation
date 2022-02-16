package com.shubham.iginsulation.rateList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.IDAssign
import com.shubham.iginsulation.R
import com.shubham.iginsulation.database.rate.RateDatabase
import com.shubham.iginsulation.database.rate.RateDatabaseDao
import com.shubham.iginsulation.databinding.FragmentVarnishBinding

class VarnishFragment : Fragment() {

    private lateinit var binding: FragmentVarnishBinding
    private lateinit var rateDatabase: RateDatabaseDao
    var id = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_varnish, container, false
        )

        rateDatabase = RateDatabase.getInstance(requireContext()).rateDatabaseDao
        binding.details.visibility = View.GONE

        IDAssign.assign(binding)
        setData()
        setListeners()
        return binding.root
    }

    private fun setListeners() {
        for (i in 1..60)
            binding.root.findViewById<TextView>(20000 + i).setOnClickListener {
                id = i.toLong()+115
                binding.details.visibility = View.VISIBLE
                val rate = rateDatabase.get(i.toLong()+115)
                binding.name.text = rate?.name
                binding.costPrice.text = rate?.costPrice.toString()
                binding.billCostPrice.text = rate?.billCostPrice.toString()
                binding.sellPrice.text = rate?.sellPrice.toString()
                binding.billSellPrice.text = rate?.billSellPrice.toString()
                binding.seller.text = rate?.seller.toString()
                binding.date.text = rate?.date.toString()
            }
        binding.edit.setOnClickListener {
            view?.findNavController()?.navigate(
                VarnishFragmentDirections.actionVarnishFragmentToVarnishEditFragment(
                    id
                )
            )
        }
    }

    private fun setData() {
        for (i in 1..60) {
            val text = rateDatabase.get(i.toLong()+115)?.sellPrice.toString()
            binding.root.findViewById<TextView>(20000 + i).text =
                text
        }
    }
}