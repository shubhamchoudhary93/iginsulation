package com.shubham.iginsulation.rateList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.shubham.iginsulation.R
import com.shubham.iginsulation.databinding.FragmentBrBinding

class BRFragment : Fragment() {

    private lateinit var binding: FragmentBrBinding
    private var rateList = listOf(10.8F, 16.9F, 138F, 177F, 274F, 325F, 485F, 485F, 625F, 625F, 800F, 800F, 1390F, 1390F)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_br, container, false
        )

        setListeners()

        return binding.root
    }

    private fun setListeners() {

        binding.onePointFive.text = String.format("%.2f", (10.8 * 1.2))
        binding.twoPointFive.text = String.format("%.2f", (16.9 * 1.2))
        binding.four.text = String.format("%.2f", (25 * 1.2))
        binding.six.text = String.format("%.2f", (34.8 * 1.2))
        binding.ten.text = String.format("%.2f", (57 * 1.2))
        binding.sixteen.text = String.format("%.2f", (95 * 1.2))
        binding.twentyFive.text = String.format("%.2f", (160 * 1.2))
        binding.thirtyFive.text = String.format("%.2f", (230 * 1.2))
        binding.fifty.text = String.format("%.2f", (315 * 1.2))
        binding.seventy.text = String.format("%.2f", (425 * 1.2))
        binding.ninetyFive.text = String.format("%.2f", (610 * 1.2))
        binding.oneTwenty.text = String.format("%.2f", (765 * 1.2))
        binding.oneFifty.text = String.format("%.2f", (900 * 1.2))
        binding.oneEightyFive.text = String.format("%.2f", (1650 * 1.2))
    }
}