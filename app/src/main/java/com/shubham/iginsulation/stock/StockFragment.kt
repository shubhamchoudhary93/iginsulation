package com.shubham.iginsulation.stock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.R
import com.shubham.iginsulation.databinding.FragmentStockBinding

class StockFragment : Fragment() {

    private lateinit var binding: FragmentStockBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_stock, container, false
        )

        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.buttonNewStock.setOnClickListener {
            view?.findNavController()
                ?.navigate(StockFragmentDirections.actionStockFragmentToStockNewFragment())
        }
        binding.buttonViewStock.setOnClickListener {
            view?.findNavController()
                ?.navigate(StockFragmentDirections.actionStockFragmentToStockListFragment())
        }
    }
}