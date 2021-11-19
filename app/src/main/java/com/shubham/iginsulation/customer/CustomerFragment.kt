package com.shubham.iginsulation.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.R
import com.shubham.iginsulation.databinding.FragmentCustomerBinding

class CustomerFragment : Fragment() {

    private lateinit var binding: FragmentCustomerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_customer, container, false
        )

        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.buttonNewCustomer.setOnClickListener {
            view?.findNavController()
                ?.navigate(CustomerFragmentDirections.actionCustomerFragmentToCustomerNewFragment())
        }
        binding.buttonViewCustomer.setOnClickListener {
            view?.findNavController()
                ?.navigate(CustomerFragmentDirections.actionCustomerFragmentToCustomerListFragment())
        }
    }
}