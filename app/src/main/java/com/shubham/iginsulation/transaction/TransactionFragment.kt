package com.shubham.iginsulation.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.R
import com.shubham.iginsulation.databinding.FragmentCustomerBinding
import com.shubham.iginsulation.databinding.FragmentTransactionBinding

class TransactionFragment : Fragment() {

    private lateinit var binding: FragmentTransactionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_transaction, container, false
        )

        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.buttonNewTransaction.setOnClickListener {
            view?.findNavController()
                ?.navigate(TransactionFragmentDirections.actionTransactionFragmentToTransactionNewFragment())
        }
        binding.buttonViewTransaction.setOnClickListener {
            view?.findNavController()
                ?.navigate(TransactionFragmentDirections.actionTransactionFragmentToTransactionListFragment())
        }
    }
}