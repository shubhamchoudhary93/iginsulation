package com.shubham.iginsulation.rateList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.R
import com.shubham.iginsulation.databinding.FragmentLeadWireBinding
import com.shubham.iginsulation.databinding.FragmentRateListBinding

class LeadWireFragment : Fragment() {

    private lateinit var binding: FragmentLeadWireBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_lead_wire, container, false
        )

        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.buttonNm.setOnClickListener {
            view?.findNavController()
                ?.navigate(LeadWireFragmentDirections.actionLeadWireFragmentToNMFragment())
        }
        binding.buttonBr.setOnClickListener {
            view?.findNavController()
                ?.navigate(LeadWireFragmentDirections.actionLeadWireFragmentToBRFragment())
        }
    }
}