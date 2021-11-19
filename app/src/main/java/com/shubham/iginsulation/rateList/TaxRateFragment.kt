package com.shubham.iginsulation.rateList

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.shubham.iginsulation.R
import com.shubham.iginsulation.databinding.FragmentTaxRateBinding

class TaxRateFragment : Fragment() {

    private lateinit var binding: FragmentTaxRateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_tax_rate, container, false
        )

        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.taxFirst.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculate()
            }
        })
        binding.taxTwo.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculate()
            }
        })
        binding.taxThree.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculate()
            }
        })
        binding.firstRate.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculate()
            }
        })
        binding.secondRate.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculate()
            }
        })
        binding.thirdRate.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculate()
            }
        })
        binding.fourthRate.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculate()
            }
        })
        binding.fifthRate.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculate()
            }
        })

        binding.reverseRate.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val reverseRate = binding.reverseRate.text.toString().toFloatOrNull()
                if (reverseRate!=null)
                    binding.reverseValue.text = ((100*reverseRate)/(109)).toString()
            }
        })

    }

    fun calculate() {
        val firstRate = binding.firstRate.text.toString().toFloatOrNull()
        val secondRate = binding.secondRate.text.toString().toFloatOrNull()
        val thirdRate = binding.thirdRate.text.toString().toFloatOrNull()
        val fourthRate = binding.fourthRate.text.toString().toFloatOrNull()
        val fifthRate = binding.fifthRate.text.toString().toFloatOrNull()
        val taxFirst = binding.taxFirst.text.toString().toFloatOrNull()
        val taxTwo = binding.taxTwo.text.toString().toFloatOrNull()
        val taxThree = binding.taxThree.text.toString().toFloatOrNull()
        if (firstRate != null) {
            if (taxFirst != null) {
                binding.firstFirst.text = (firstRate * (100+taxFirst) / 100F).toString()
            }
            if (taxTwo != null) {
                binding.firstTwo.text = (firstRate * (100+taxTwo) / 100F).toString()
            }
            if (taxThree != null) {
                binding.firstThree.text = (firstRate * (100+taxThree) / 100F).toString()
            }
        }
        if (secondRate != null) {
            if (taxFirst != null) {
                binding.secondFirst.text = (secondRate * (100+taxFirst) / 100F).toString()
            }
            if (taxTwo != null) {
                binding.secondTwo.text = (secondRate * (100+taxTwo) / 100F).toString()
            }
            if (taxThree != null) {
                binding.secondThree.text = (secondRate * (100+taxThree) / 100F).toString()
            }
        }
        if (thirdRate != null) {
            if (taxFirst != null) {
                binding.thirdFirst.text = (thirdRate * (100+taxFirst) / 100F).toString()
            }
            if (taxTwo != null) {
                binding.thirdTwo.text = (thirdRate * (100+taxTwo) / 100F).toString()
            }
            if (taxThree != null) {
                binding.thirdThree.text = (thirdRate * (100+taxThree) / 100F).toString()
            }
        }
        if (fourthRate != null) {
            if (taxFirst != null) {
                binding.fourthFirst.text = (fourthRate * (100+taxFirst) / 100F).toString()
            }
            if (taxTwo != null) {
                binding.fourthTwo.text = (fourthRate * (100+taxTwo) / 100F).toString()
            }
            if (taxThree != null) {
                binding.fourthThree.text = (fourthRate * (100+taxThree) / 100F).toString()
            }
        }
        if (fifthRate != null) {
            if (taxFirst != null) {
                binding.fifthFirst.text = (fifthRate * (100+taxFirst) / 100F).toString()
            }
            if (taxTwo != null) {
                binding.fifthTwo.text = (fifthRate * (100+taxTwo) / 100F).toString()
            }
            if (taxThree != null) {
                binding.fifthThree.text = (fifthRate * (100+taxThree) / 100F).toString()
            }
        }


    }
}