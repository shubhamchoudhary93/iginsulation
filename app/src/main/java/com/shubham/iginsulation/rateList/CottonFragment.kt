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
import com.shubham.iginsulation.databinding.FragmentCottonBinding

class CottonFragment : Fragment() {

    private lateinit var binding: FragmentCottonBinding
    private lateinit var rateDatabase: RateDatabaseDao
    var id = 0L

    val name = arrayOf(
        "Cotton 1",
        "Cotton 2",
        "Cotton 3",
        "Cotton 4",
        "Cotton 5",
        "Cotton 6",
        "Cotton 8",
        "Cotton 10",
        "Cotton shubham 2",
        "Cotton ganga 2"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_cotton, container, false
        )

        rateDatabase = RateDatabase.getInstance(requireContext()).rateDatabaseDao
        binding.details.visibility = View.GONE

        IDAssign.assign(binding)
        setData()
        setListeners()

        return binding.root
    }

    private fun setListeners() {
        for (i in 1..10)
            binding.root.findViewById<TextView>(20000 + i).setOnClickListener {
                binding.details.visibility = View.VISIBLE
                val rate = rateDatabase.get(name[i-1])
                id = rate?.id!!
                binding.name.text = rate.name
                binding.costPrice.text = rate.costPrice.toString()
                binding.sellPrice.text = rate.sellPrice.toString()
                binding.date.text = rate.date
            }
        binding.edit.setOnClickListener {
            view?.findNavController()?.navigate(
                CottonFragmentDirections.actionCottonFragmentToCottonEditFragment(
                    id
                )
            )
        }
    }

    private fun setData() {
        for (i in 1..10) {
            val text = rateDatabase.get(name[i-1])?.sellPrice.toString()
            binding.root.findViewById<TextView>(20000 + i).text =
                text
        }
    }

}