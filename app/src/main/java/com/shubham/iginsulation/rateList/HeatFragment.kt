package com.shubham.iginsulation.rateList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.shubham.iginsulation.IDAssign
import com.shubham.iginsulation.R
import com.shubham.iginsulation.databinding.FragmentHeatBinding
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

class HeatFragment : Fragment() {

    private lateinit var binding: FragmentHeatBinding
    private var buttonOneValue = 42F
    private var buttonTwoValue = 40F
    private var buttonThreeValue = 35F
    private var buttonFourValue = 30F
    private var buttonFiveValue = 25F
    private var buttonSixValue = 15F

    private var rateList = listOf(
        6F,
        5F,
        2.1F,
        2.5F,
        2.9F,
        3.8F,
        4.25F,
        4.6F,
        5.5F,
        6.4F,
        7.3F,
        7.7F,
        8.6F,
        7.5F,
        7.85F,
        8.5F,
        9.3F,
        10.2F,
        11F,
        11.7F,
        12.5F,
        14.5F,
        16.5F,
        19F,
        23.75F,
        25F,
        31F,
        37.75F,
        42.5F,
        51.5F,
        54.8F,
        69.5F,
        77F,
        85F,
        92.5F,
        123F,
        157F,
        175F,
        225F,
        285F,
        375F,
        470F,
        650F
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_heat, container, false
        )

        setListeners()
        IDAssign.assign(binding)

        binding.valuesLayout.visibility = View.GONE

        val buttonOneText = "$buttonOneValue%"
        val buttonTwoText = "$buttonTwoValue%"
        val buttonThreeText = "$buttonThreeValue%"
        val buttonFourText = "$buttonFourValue%"
        val buttonFiveText = "$buttonFiveValue%"
        val buttonSixText = "$buttonSixValue%"

        binding.buttonOne.text = buttonOneText
        binding.buttonTwo.text = buttonTwoText
        binding.buttonThree.text = buttonThreeText
        binding.buttonFour.text = buttonFourText
        binding.buttonFive.text = buttonFiveText
        binding.buttonSix.text = buttonSixText

        return binding.root
    }

    private fun setListeners() {

        binding.buttonOne.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.headRate.text = buttonOneValue.toString()
            for (i in 1..42) {
                val j = 50000 + i
                val num = rateList[i - 1] * (1 - (buttonOneValue / 100))
                val df = DecimalFormat("#.###")
                df.roundingMode = RoundingMode.CEILING
                binding.root.findViewById<TextView>(j).text =
                    df.format(num).toString()
            }
        }

        binding.buttonTwo.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.headRate.text = buttonTwoValue.toString()
            for (i in 1..42) {
                val j = 50000 + i
                binding.root.findViewById<TextView>(j).text =
                    (rateList[i - 1] * (1 - (buttonTwoValue / 100))).toString()
            }
        }

        binding.buttonThree.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.headRate.text = buttonThreeValue.toString()
            for (i in 1..42) {
                val j = 50000 + i
                binding.root.findViewById<TextView>(j).text =
                    (rateList[i - 1] * (1 - (buttonThreeValue / 100))).toString()
            }
        }

        binding.buttonFour.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.headRate.text = buttonFourValue.toString()
            for (i in 1..42) {
                val j = 50000 + i
                binding.root.findViewById<TextView>(j).text =
                    (rateList[i - 1] * (1 - (buttonFourValue / 100))).toString()
            }
        }

        binding.buttonFive.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.headRate.text = buttonFiveValue.toString()
            for (i in 1..42) {
                val j = 50000 + i
                binding.root.findViewById<TextView>(j).text =
                    (rateList[i - 1] * (1 - (buttonFiveValue / 100))).toString()
            }
        }

        binding.buttonSix.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.headRate.text = buttonSixValue.toString()
            for (i in 1..42) {
                val j = 50000 + i
                binding.root.findViewById<TextView>(j).text =
                    (rateList[i - 1] * (1 - (buttonSixValue / 100))).toString()
            }
        }

        binding.buttonGo.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE

            val perc = binding.percentage.text.toString().toFloatOrNull()
            binding.headRate.text = perc.toString()
            if (perc != null) {
                for (i in 1..42) {
                    val j = 50000 + i
                    binding.root.findViewById<TextView>(j).text =
                        (rateList[i - 1] * (1 - (perc / 100))).toString()
                }
            }
        }
    }
}