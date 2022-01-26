package com.shubham.iginsulation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.databinding.FragmentBillingBinding

class BillingFragment: Fragment() {

    private lateinit var binding: FragmentBillingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_billing, container, false
        )

        setListeners()

        return binding.root
    }

    private fun setListeners() {

        binding.buttonCustomer.setOnClickListener {
            view?.findNavController()
                ?.navigate(BillingFragmentDirections.actionBillingFragmentToCustomerFragment())
        }

        binding.buttonStock.setOnClickListener {
            view?.findNavController()
                ?.navigate(BillingFragmentDirections.actionBillingFragmentToStockFragment())
        }

        binding.buttonSale.setOnClickListener {
            view?.findNavController()
                ?.navigate(BillingFragmentDirections.actionBillingFragmentToSaleFragment())
        }

        binding.buttonTransaction.setOnClickListener {
            view?.findNavController()
                ?.navigate(BillingFragmentDirections.actionBillingFragmentToTransactionFragment())
        }
    }
}