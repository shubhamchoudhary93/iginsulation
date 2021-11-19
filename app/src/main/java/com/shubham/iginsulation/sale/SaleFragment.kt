package com.shubham.iginsulation.sale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.R
import com.shubham.iginsulation.databinding.FragmentSaleBinding

class SaleFragment : Fragment() {

    private lateinit var binding: FragmentSaleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_sale, container, false
        )

        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.buttonNewSale.setOnClickListener {
            view?.findNavController()
                ?.navigate(SaleFragmentDirections.actionSaleFragmentToSaleNewFragment())
        }
        binding.buttonViewSale.setOnClickListener {
            view?.findNavController()
                ?.navigate(SaleFragmentDirections.actionSaleFragmentToSaleListFragment())
        }
    }
}