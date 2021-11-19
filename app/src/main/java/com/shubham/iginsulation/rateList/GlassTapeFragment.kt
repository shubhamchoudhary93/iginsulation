package com.shubham.iginsulation.rateList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.shubham.iginsulation.R
import com.shubham.iginsulation.databinding.FragmentGlassTapeBinding
import com.shubham.iginsulation.databinding.FragmentVarnishBinding

class GlassTapeFragment : Fragment() {

    private lateinit var binding: FragmentGlassTapeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_glass_tape, container, false
        )

        return binding.root
    }

}