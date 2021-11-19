package com.shubham.iginsulation.rateList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.R
import com.shubham.iginsulation.databinding.FragmentSleeveBinding

class SleeveFragment : Fragment() {

    private lateinit var binding: FragmentSleeveBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_sleeve, container, false
        )

        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.buttonFClass.setOnClickListener {
            view?.findNavController()
                ?.navigate(SleeveFragmentDirections.actionSleeveFragmentToFClassFragment())
        }
        binding.buttonBClass.setOnClickListener {
            view?.findNavController()
                ?.navigate(SleeveFragmentDirections.actionSleeveFragmentToBClassFragment())
        }
        binding.buttonHClass.setOnClickListener {
            view?.findNavController()
                ?.navigate(SleeveFragmentDirections.actionSleeveFragmentToHClassFragment())
        }
        binding.buttonChina.setOnClickListener {
            view?.findNavController()
                ?.navigate(SleeveFragmentDirections.actionSleeveFragmentToChinaFragment())
        }
        binding.buttonHeat.setOnClickListener {
            view?.findNavController()
                ?.navigate(SleeveFragmentDirections.actionSleeveFragmentToHeatFragment())
        }
        binding.buttonCotton.setOnClickListener {
            view?.findNavController()
                ?.navigate(SleeveFragmentDirections.actionSleeveFragmentToCottonFragment())
        }
    }
}