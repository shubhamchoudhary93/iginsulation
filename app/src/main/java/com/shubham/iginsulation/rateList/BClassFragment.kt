package com.shubham.iginsulation.rateList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.shubham.iginsulation.IDAssign.assign
import com.shubham.iginsulation.R
import com.shubham.iginsulation.databinding.FragmentBClassBinding
import kotlin.math.roundToInt

class BClassFragment : Fragment() {

    private lateinit var binding: FragmentBClassBinding
    private var buttonOneValue = 5F
    private var buttonTwoValue = 10F
    private var buttonThreeValue = 15F
    private var buttonFourValue = 20F
    private var buttonFiveValue = 25F
    private var buttonSixValue = 30F

    private var rateList = listOf(98F, 128F, 138F, 177F, 274F, 325F, 485F, 485F, 625F, 625F, 800F, 800F, 1390F, 1390F)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_b_class, container, false
        )

        setListeners()
        assign(binding)

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
            for (i in 1..15) {
                val j = 40000 + i
                binding.root.findViewById<TextView>(j).text = (rateList[i - 1] * (1 + (buttonOneValue / 100))).roundToInt().toString()
            }
        }

        binding.buttonTwo.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.headRate.text = buttonTwoValue.toString()

            for (i in 1..15) {
                val j = 40000 + i
                binding.root.findViewById<TextView>(j).text = (rateList[i - 1] * (1 + (buttonTwoValue / 100))).roundToInt().toString()
            }
        }
        binding.buttonThree.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.headRate.text = buttonThreeValue.toString()
            for (i in 1..15) {
                val j = 40000 + i
                binding.root.findViewById<TextView>(j).text = (rateList[i - 1] * (1 + (buttonThreeValue / 100))).roundToInt().toString()
            }
        }
        binding.buttonFour.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.headRate.text = buttonFourValue.toString()
            for (i in 1..15) {
                val j = 40000 + i
                binding.root.findViewById<TextView>(j).text = (rateList[i - 1] * (1 + (buttonFourValue / 100))).roundToInt().toString()
            }
        }
        binding.buttonFive.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.headRate.text = buttonFiveValue.toString()
            for (i in 1..15) {
                val j = 40000 + i
                binding.root.findViewById<TextView>(j).text = (rateList[i - 1] * (1 + (buttonFiveValue / 100))).roundToInt().toString()
            }
        }
        binding.buttonSix.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.headRate.text = buttonSixValue.toString()
            for (i in 1..15) {
                val j = 40000 + i
                binding.root.findViewById<TextView>(j).text = (rateList[i - 1] * (1 + (buttonSixValue / 100))).roundToInt().toString()
            }
        }
        binding.buttonGo.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE

            val perc = binding.percentage.text.toString().toFloatOrNull()
            binding.headRate.text = perc.toString()
            if (perc != null) {
                for (i in 1..15) {
                    val j = 40000 + i
                    binding.root.findViewById<TextView>(j).text = (rateList[i - 1] * (1 + (perc / 100))).roundToInt().toString()
                }
            }
        }


    }
}