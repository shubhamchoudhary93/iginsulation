package com.shubham.iginsulation.rateList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.shubham.iginsulation.R
import com.shubham.iginsulation.databinding.FragmentHeatBinding

class HeatFragment : Fragment() {

    private lateinit var binding: FragmentHeatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_heat, container, false
        )

        setListeners()

        binding.valuesLayout.visibility = View.GONE
        return binding.root
    }

    private fun setListeners() {
        binding.buttonOne.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.pointSix.text = String.format("%.4f", (6 * 0.58))
            binding.pointEight.text = String.format("%.4f", (5 * 0.58))
            binding.one.text = String.format("%.4f", (2.1 * 0.58))
            binding.onePointFive.text = String.format("%.4f", (2.5 * 0.58))
            binding.two.text = String.format("%.4f", (2.9 * 0.58))
            binding.twoPointFive.text = String.format("%.4f", (3.8 * 0.58))
            binding.three.text = String.format("%.4f", (4.25 * 0.58))
            binding.threePointFive.text = String.format("%.4f", (4.6 * 0.58))
            binding.four.text = String.format("%.4f", (5.5 * 0.58))
            binding.fourPointFive.text = String.format("%.4f", (6.4 * 0.58))
            binding.five.text = String.format("%.4f", (7.3 * 0.58))
            binding.fivePointFive.text = String.format("%.4f", (7.7 * 0.58))
            binding.six.text = String.format("%.4f", (8.6 * 0.58))
            binding.seven.text = String.format("%.4f", (7.85 * 0.58))
            binding.eight.text = String.format("%.4f", (8.5 * 0.58))
            binding.nine.text = String.format("%.4f", (9.3 * 0.58))
            binding.ten.text = String.format("%.4f", (10.2 * 0.58))
            binding.eleven.text = String.format("%.4f", (11 * 0.58))
            binding.twelve.text = String.format("%.4f", (11.7 * 0.58))
        }
        binding.buttonTwo.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.pointSix.text = String.format("%.4f", (6 * 0.6))
            binding.pointEight.text = String.format("%.4f", (5 * 0.6))
            binding.one.text = String.format("%.4f", (2.1 * 0.6))
            binding.onePointFive.text = String.format("%.4f", (2.5 * 0.6))
            binding.two.text = String.format("%.4f", (2.9 * 0.6))
            binding.twoPointFive.text = String.format("%.4f", (3.8 * 0.6))
            binding.three.text = String.format("%.4f", (4.25 * 0.6))
            binding.threePointFive.text = String.format("%.4f", (4.6 * 0.6))
            binding.four.text = String.format("%.4f", (5.5 * 0.6))
            binding.fourPointFive.text = String.format("%.4f", (6.4 * 0.6))
            binding.five.text = String.format("%.4f", (7.3 * 0.6))
            binding.fivePointFive.text = String.format("%.4f", (7.7 * 0.6))
            binding.six.text = String.format("%.4f", (8.6 * 0.6))
            binding.seven.text = String.format("%.4f", (7.85 * 0.6))
            binding.eight.text = String.format("%.4f", (8.5 * 0.6))
            binding.nine.text = String.format("%.4f", (9.3 * 0.6))
            binding.ten.text = String.format("%.4f", (10.2 * 0.6))
            binding.eleven.text = String.format("%.4f", (11 * 0.6))
            binding.twelve.text = String.format("%.4f", (11.7 * 0.6))
        }
        binding.buttonThree.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.pointSix.text = String.format("%.4f", (6 * 0.65))
            binding.pointEight.text = String.format("%.4f", (5 * 0.65))
            binding.one.text = String.format("%.4f", (2.1 * 0.65))
            binding.onePointFive.text = String.format("%.4f", (2.5 * 0.65))
            binding.two.text = String.format("%.4f", (2.9 * 0.65))
            binding.twoPointFive.text = String.format("%.4f", (3.8 * 0.65))
            binding.three.text = String.format("%.4f", (4.25 * 0.65))
            binding.threePointFive.text = String.format("%.4f", (4.6 * 0.65))
            binding.four.text = String.format("%.4f", (5.5 * 0.65))
            binding.fourPointFive.text = String.format("%.4f", (6.4 * 0.65))
            binding.five.text = String.format("%.4f", (7.3 * 0.65))
            binding.fivePointFive.text = String.format("%.4f", (7.7 * 0.65))
            binding.six.text = String.format("%.4f", (8.6 * 0.65))
            binding.seven.text = String.format("%.4f", (7.85 * 0.65))
            binding.eight.text = String.format("%.4f", (8.5 * 0.65))
            binding.nine.text = String.format("%.4f", (9.3 * 0.65))
            binding.ten.text = String.format("%.4f", (10.2 * 0.65))
            binding.eleven.text = String.format("%.4f", (11 * 0.65))
            binding.twelve.text = String.format("%.4f", (11.7 * 0.65))
        }
        binding.buttonFour.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.pointSix.text = String.format("%.4f", (6 * 0.7))
            binding.pointEight.text = String.format("%.4f", (5 * 0.7))
            binding.one.text = String.format("%.4f", (2.1 * 0.7))
            binding.onePointFive.text = String.format("%.4f", (2.5 * 0.7))
            binding.two.text = String.format("%.4f", (2.9 * 0.7))
            binding.twoPointFive.text = String.format("%.4f", (3.8 * 0.7))
            binding.three.text = String.format("%.4f", (4.25 * 0.7))
            binding.threePointFive.text = String.format("%.4f", (4.6 * 0.7))
            binding.four.text = String.format("%.4f", (5.5 * 0.7))
            binding.fourPointFive.text = String.format("%.4f", (6.4 * 0.7))
            binding.five.text = String.format("%.4f", (7.3 * 0.7))
            binding.fivePointFive.text = String.format("%.4f", (7.7 * 0.7))
            binding.six.text = String.format("%.4f", (8.6 * 0.7))
            binding.seven.text = String.format("%.4f", (7.85 * 0.7))
            binding.eight.text = String.format("%.4f", (8.5 * 0.7))
            binding.nine.text = String.format("%.4f", (9.3 * 0.7))
            binding.ten.text = String.format("%.4f", (10.2 * 0.7))
            binding.eleven.text = String.format("%.4f", (11 * 0.7))
            binding.twelve.text = String.format("%.4f", (11.7 * 0.7))
        }
        binding.buttonFive.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.pointSix.text = String.format("%.4f", (6 * 0.75))
            binding.pointEight.text = String.format("%.4f", (5 * 0.75))
            binding.one.text = String.format("%.4f", (2.1 * 0.75))
            binding.onePointFive.text = String.format("%.4f", (2.5 * 0.75))
            binding.two.text = String.format("%.4f", (2.9 * 0.75))
            binding.twoPointFive.text = String.format("%.4f", (3.8 * 0.75))
            binding.three.text = String.format("%.4f", (4.25 * 0.75))
            binding.threePointFive.text = String.format("%.4f", (4.6 * 0.75))
            binding.four.text = String.format("%.4f", (5.5 * 0.75))
            binding.fourPointFive.text = String.format("%.4f", (6.4 * 0.75))
            binding.five.text = String.format("%.4f", (7.3 * 0.75))
            binding.fivePointFive.text = String.format("%.4f", (7.7 * 0.75))
            binding.six.text = String.format("%.4f", (8.6 * 0.75))
            binding.seven.text = String.format("%.4f", (7.85 * 0.75))
            binding.eight.text = String.format("%.4f", (8.5 * 0.75))
            binding.nine.text = String.format("%.4f", (9.3 * 0.75))
            binding.ten.text = String.format("%.4f", (10.2 * 0.75))
            binding.eleven.text = String.format("%.4f", (11 * 0.75))
            binding.twelve.text = String.format("%.4f", (11.7 * 0.75))
        }
        binding.buttonSix.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.pointSix.text = String.format("%.4f", (6 * 0.8))
            binding.pointEight.text = String.format("%.4f", (5 * 0.8))
            binding.one.text = String.format("%.4f", (2.1 * 0.8))
            binding.onePointFive.text = String.format("%.4f", (2.5 * 0.8))
            binding.two.text = String.format("%.4f", (2.9 * 0.8))
            binding.twoPointFive.text = String.format("%.4f", (3.8 * 0.8))
            binding.three.text = String.format("%.4f", (4.25 * 0.8))
            binding.threePointFive.text = String.format("%.4f", (4.6 * 0.8))
            binding.four.text = String.format("%.4f", (5.5 * 0.8))
            binding.fourPointFive.text = String.format("%.4f", (6.4 * 0.8))
            binding.five.text = String.format("%.4f", (7.3 * 0.8))
            binding.fivePointFive.text = String.format("%.4f", (7.7 * 0.8))
            binding.six.text = String.format("%.4f", (8.6 * 0.8))
            binding.seven.text = String.format("%.4f", (7.85 * 0.8))
            binding.eight.text = String.format("%.4f", (8.5 * 0.8))
            binding.nine.text = String.format("%.4f", (9.3 * 0.8))
            binding.ten.text = String.format("%.4f", (10.2 * 0.8))
            binding.eleven.text = String.format("%.4f", (11 * 0.8))
            binding.twelve.text = String.format("%.4f", (11.7 * 0.8))
        }
        binding.buttonGo.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE

            var perc = binding.percentage.text.toString().toFloatOrNull()
            if (perc != null) {
                perc = 1f - perc / 100
                binding.pointSix.text = String.format("%.4f", (6 * perc))
                binding.pointEight.text = String.format("%.4f", (5 * perc))
                binding.one.text = String.format("%.4f", (2.1 * perc))
                binding.onePointFive.text = String.format("%.4f", (2.5 * perc))
                binding.two.text = String.format("%.4f", (2.9 * perc))
                binding.twoPointFive.text = String.format("%.4f", (3.8 * perc))
                binding.three.text = String.format("%.4f", (4.25 * perc))
                binding.threePointFive.text = String.format("%.4f", (4.6 * perc))
                binding.four.text = String.format("%.4f", (5.5 * perc))
                binding.fourPointFive.text = String.format("%.4f", (6.4 * perc))
                binding.five.text = String.format("%.4f", (7.3 * perc))
                binding.fivePointFive.text = String.format("%.4f", (7.7 * perc))
                binding.six.text = String.format("%.4f", (8.6 * perc))
                binding.seven.text = String.format("%.4f", (7.85 * perc))
                binding.eight.text = String.format("%.4f", (8.5 * perc))
                binding.nine.text = String.format("%.4f", (9.3 * perc))
                binding.ten.text = String.format("%.4f", (10.2 * perc))
                binding.eleven.text = String.format("%.4f", (11 * perc))
                binding.twelve.text = String.format("%.4f", (11.7 * perc))
            }
        }


    }
}