package com.shubham.iginsulation.rateList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.R
import com.shubham.iginsulation.databinding.FragmentRateListBinding

class RateListFragment : Fragment() {

    private lateinit var binding: FragmentRateListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_rate_list, container, false
        )

        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.buttonSleeve.setOnClickListener {
            view?.findNavController()
                ?.navigate(RateListFragmentDirections.actionRateListFragmentToSleeveFragment())
        }
        binding.buttonVarnish.setOnClickListener {
            view?.findNavController()
                ?.navigate(RateListFragmentDirections.actionRateListFragmentToVarnishFragment())
        }
        binding.buttonLeadWire.setOnClickListener {
            view?.findNavController()
                ?.navigate(RateListFragmentDirections.actionRateListFragmentToLeadWireFragment())
        }
        binding.buttonGlassTape.setOnClickListener {
            view?.findNavController()
                ?.navigate(RateListFragmentDirections.actionRateListFragmentToGlassTapeFragment())
        }
        binding.buttonTaxRate.setOnClickListener {
            view?.findNavController()
                ?.navigate(RateListFragmentDirections.actionRateListFragmentToTaxRateFragment())
        }
    }
}