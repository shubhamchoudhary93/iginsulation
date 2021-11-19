package com.shubham.iginsulation.committee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.R
import com.shubham.iginsulation.databinding.FragmentCommitteeBinding
import com.shubham.iginsulation.databinding.FragmentCustomerBinding

class CommitteeFragment : Fragment() {

    private lateinit var binding: FragmentCommitteeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_committee, container, false
        )

        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.buttonCalculate.setOnClickListener {
            view?.findNavController()
                ?.navigate(CommitteeFragmentDirections.actionCommitteeFragmentToCommitteeCalculator())
        }

    }
}