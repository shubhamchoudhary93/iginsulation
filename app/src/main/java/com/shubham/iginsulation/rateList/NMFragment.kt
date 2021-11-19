package com.shubham.iginsulation.rateList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.shubham.iginsulation.R
import com.shubham.iginsulation.databinding.FragmentNmBinding

class NMFragment : Fragment() {

    private lateinit var binding: FragmentNmBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_nm, container, false
        )

        setListeners()

        binding.valuesLayout.visibility = View.GONE
        return binding.root
    }

    private fun setListeners() {
        binding.buttonOne.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.one.text = String.format("%.2f", (5.60 * 1.8))
            binding.onePointFive.text = String.format("%.2f", (7.20 * 1.8))
            binding.twoPointFive.text = String.format("%.2f", (9.95 * 1.8))
            binding.four.text = String.format("%.2f", (15.20 * 1.8))
            binding.six.text = String.format("%.2f", (21.00 * 1.8))
            binding.ten.text = String.format("%.2f", (34.50 * 1.8))
            binding.sixteen.text = String.format("%.2f", (56 * 1.8))
            binding.twentyFive.text = String.format("%.2f", (97 * 1.8))
            binding.thirtyFive.text = String.format("%.2f", (135 * 1.8))
            binding.fifty.text = String.format("%.2f", (183 * 1.8))
            binding.seventy.text = String.format("%.2f", (250 * 1.8))
            binding.nintyFive.text = String.format("%.2f", (388 * 1.8))
            binding.oneTwenty.text = String.format("%.2f", (442 * 1.8))
            binding.oneFifty.text = String.format("%.2f", (525 * 1.8))
            binding.oneEightyFive.text = String.format("%.2f", (1650 * 1.8))
        }
        binding.buttonTwo.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.one.text = String.format("%.2f", (5.60 * 1.9))
            binding.onePointFive.text = String.format("%.2f", (7.20 * 1.9))
            binding.twoPointFive.text = String.format("%.2f", (9.95 * 1.9))
            binding.four.text = String.format("%.2f", (15.20 * 1.9))
            binding.six.text = String.format("%.2f", (21.00 * 1.9))
            binding.ten.text = String.format("%.2f", (34.50 * 1.9))
            binding.sixteen.text = String.format("%.2f", (56 * 1.9))
            binding.twentyFive.text = String.format("%.2f", (97 * 1.9))
            binding.thirtyFive.text = String.format("%.2f", (135 * 1.9))
            binding.fifty.text = String.format("%.2f", (183 * 1.9))
            binding.seventy.text = String.format("%.2f", (250 * 1.9))
            binding.nintyFive.text = String.format("%.2f", (388 * 1.9))
            binding.oneTwenty.text = String.format("%.2f", (442 * 1.9))
            binding.oneFifty.text = String.format("%.2f", (525 * 1.9))
            binding.oneEightyFive.text = String.format("%.2f", (1650 * 1.9))
        }
        binding.buttonThree.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.one.text = String.format("%.2f", (5.60 * 2))
            binding.onePointFive.text = String.format("%.2f", (7.20 * 2))
            binding.twoPointFive.text = String.format("%.2f", (9.95 * 2))
            binding.four.text = String.format("%.2f", (15.20 * 2))
            binding.six.text = String.format("%.2f", (21.00 * 2))
            binding.ten.text = String.format("%.2f", (34.50 * 2))
            binding.sixteen.text = (56 * 2).toString()
            binding.twentyFive.text = (97 * 2).toString()
            binding.thirtyFive.text = (135 * 2).toString()
            binding.fifty.text = (183 * 2).toString()
            binding.seventy.text = (250 * 2).toString()
            binding.nintyFive.text = (388 * 2).toString()
            binding.oneTwenty.text = (442 * 2).toString()
            binding.oneFifty.text = (525 * 2).toString()
            binding.oneEightyFive.text = (1650 * 2).toString()
        }
        
        binding.buttonGo.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE

            var perc = binding.percentage.text.toString().toFloatOrNull()
            if (perc != null) {
                perc = perc / 100 + 1.0f
                binding.one.text = String.format("%.2f", (5.60 * perc))
                binding.onePointFive.text = String.format("%.2f", (7.20 * perc))
                binding.twoPointFive.text = String.format("%.2f", (9.95 * perc))
                binding.four.text = String.format("%.2f", (15.20 * perc))
                binding.six.text = String.format("%.2f", (21.00 * perc))
                binding.ten.text = String.format("%.2f", (34.50 * perc))
                binding.sixteen.text = String.format("%.2f", (56 * perc))
                binding.twentyFive.text = String.format("%.2f", (97 * perc))
                binding.thirtyFive.text = String.format("%.2f", (135 * perc))
                binding.fifty.text = String.format("%.2f", (183 * perc))
                binding.seventy.text = String.format("%.2f", (250 * perc))
                binding.nintyFive.text = String.format("%.2f", (388 * perc))
                binding.oneTwenty.text = String.format("%.2f", (442 * perc))
                binding.oneFifty.text = String.format("%.2f", (525 * perc))
                binding.oneEightyFive.text = String.format("%.2f", (1650 * perc))
            }
        }


    }
}