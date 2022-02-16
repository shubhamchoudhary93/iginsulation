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
import com.shubham.iginsulation.database.rate.Rate
import com.shubham.iginsulation.database.rate.RateDatabase
import com.shubham.iginsulation.database.rate.RateDatabaseDao
import com.shubham.iginsulation.databinding.FragmentVarnishBinding

class VarnishFragment : Fragment() {

    private lateinit var binding: FragmentVarnishBinding
    private lateinit var rateDatabase: RateDatabaseDao
    var id = 0L

    val name = arrayOf(
         "Mak 200ml",
         "Mak 500ml",
         "Mak 1l",
         "Mak 5l",
         "Mak 20l",
         "Pio 200ml",
         "Pio 500ml",
         "Pio 1l",
         "Pio 5l",
         "Pio 20l",
         "Ray 200ml",
         "Ray 500ml",
         "Ray 1l",
         "Ray 5l",
         "Ray 20l",
         "Bake 200ml",
         "Bake 500ml",
         "Bake 1l",
         "Bake 5l",
         "Bake 20l",
         "Super 200ml",
         "Super 500ml",
         "Super 1l",
         "Super 5l",
         "Super 20l",
         "Dia 200ml",
         "Dia 500ml",
         "Dia 1l",
         "Dia 5l",
         "Dia 20l",
         "Wil 200ml",
         "Wil 500ml",
         "Wil 1l",
         "Wil 5l",
         "Wil 20l",
         "Insu 200ml",
         "Insu 500ml",
         "Insu 1l",
         "Insu 5l",
         "Insu 20l",
         "A1 200ml",
         "A1 500ml",
         "A1 1l",
         "A1 5l",
         "A1 20l",
         "A1W 200ml",
         "A1W 500ml",
         "A1W 1l",
         "A1W 5l",
         "A1W 20l",
         "Beck 200ml",
         "Beck 500ml",
         "Beck 1l",
         "Beck 5l",
         "Beck 20l",
         "Jyoti 200ml",
         "Jyoti 500ml",
         "Jyoti 1l",
         "Jyoti 5l",
         "Jyoti 20l"
    )

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

                binding.details.visibility = View.VISIBLE
                val rate = rateDatabase.get(name[i-1])
                id = rate?.id!!
                binding.name.text = rate.name
                binding.costPrice.text = rate.costPrice.toString()
                binding.billCostPrice.text = rate.billCostPrice.toString()
                binding.sellPrice.text = rate.sellPrice.toString()
                binding.billSellPrice.text = rate.billSellPrice.toString()
                binding.seller.text = rate.seller
                binding.date.text = rate.date
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
            val text = rateDatabase.get(name[i-1])?.sellPrice.toString()
            binding.root.findViewById<TextView>(20000 + i).text =
                text
        }
    }
}