package com.shubham.iginsulation.rateList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.shubham.iginsulation.R
import com.shubham.iginsulation.databinding.FragmentFClassBinding
import kotlin.math.roundToInt

class FClassFragment : Fragment() {

    private lateinit var binding: FragmentFClassBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_f_class, container, false
        )

        setListeners()

        binding.valuesLayout.visibility = View.GONE
        return binding.root
    }

    private fun setListeners() {
        binding.buttonOne.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.one.text = (98 * 1.4).roundToInt().toString()
            binding.onePointFive.text = (128 * 1.4).roundToInt().toString()
            binding.two.text = (138 * 1.4).roundToInt().toString()
            binding.three.text = (177 * 1.4).roundToInt().toString()
            binding.four.text = (222 * 1.4).roundToInt().toString()
            binding.five.text = (274 * 1.4).roundToInt().toString()
            binding.six.text = (325 * 1.4).roundToInt().toString()
            binding.eight.text = (485 * 1.4).roundToInt().toString()
            binding.eightPacket.text = (485 / 2 * 1.4).roundToInt().toString()
            binding.ten.text = (625 * 1.4).roundToInt().toString()
            binding.tenPacket.text = (625 / 2 * 1.4).roundToInt().toString()
            binding.twelve.text = (800 * 1.4).roundToInt().toString()
            binding.twelvePacket.text = (800 / 4 * 1.4).roundToInt().toString()
            binding.sixteen.text = (1120 * 1.4).roundToInt().toString()
            binding.sixteenPacket.text = (1120 / 4 * 1.4).roundToInt().toString()
        }
        binding.buttonTwo.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.one.text = (98 * 1.45).roundToInt().toString()
            binding.onePointFive.text = (128 * 1.45).roundToInt().toString()
            binding.two.text = (138 * 1.45).roundToInt().toString()
            binding.three.text = (177 * 1.45).roundToInt().toString()
            binding.four.text = (222 * 1.45).roundToInt().toString()
            binding.five.text = (274 * 1.45).roundToInt().toString()
            binding.six.text = (325 * 1.45).roundToInt().toString()
            binding.eight.text = (485 * 1.45).roundToInt().toString()
            binding.eightPacket.text = (485 / 2 * 1.45).roundToInt().toString()
            binding.ten.text = (625 * 1.45).roundToInt().toString()
            binding.tenPacket.text = (625 / 2 * 1.45).roundToInt().toString()
            binding.twelve.text = (800 * 1.45).roundToInt().toString()
            binding.twelvePacket.text = (800 / 4 * 1.45).roundToInt().toString()
            binding.sixteen.text = (1120 * 1.45).roundToInt().toString()
            binding.sixteenPacket.text = (1120 / 4 * 1.45).roundToInt().toString()
        }
        binding.buttonThree.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.one.text = (98 * 1.48).roundToInt().toString()
            binding.onePointFive.text = (128 * 1.48).roundToInt().toString()
            binding.two.text = (138 * 1.48).roundToInt().toString()
            binding.three.text = (177 * 1.48).roundToInt().toString()
            binding.four.text = (222 * 1.48).roundToInt().toString()
            binding.five.text = (274 * 1.48).roundToInt().toString()
            binding.six.text = (325 * 1.48).roundToInt().toString()
            binding.eight.text = (485 * 1.48).roundToInt().toString()
            binding.eightPacket.text = (485 / 2 * 1.48).roundToInt().toString()
            binding.ten.text = (625 * 1.48).roundToInt().toString()
            binding.tenPacket.text = (625 / 2 * 1.48).roundToInt().toString()
            binding.twelve.text = (800 * 1.48).roundToInt().toString()
            binding.twelvePacket.text = (800 / 4 * 1.48).roundToInt().toString()
            binding.sixteen.text = (1120 * 1.48).roundToInt().toString()
            binding.sixteenPacket.text = (1120 / 4 * 1.48).roundToInt().toString()
        }
        binding.buttonFour.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.one.text = (98 * 1.5).roundToInt().toString()
            binding.onePointFive.text = (128 * 1.5).roundToInt().toString()
            binding.two.text = (138 * 1.5).roundToInt().toString()
            binding.three.text = (177 * 1.5).roundToInt().toString()
            binding.four.text = (222 * 1.5).roundToInt().toString()
            binding.five.text = (274 * 1.5).roundToInt().toString()
            binding.six.text = (325 * 1.5).roundToInt().toString()
            binding.eight.text = (485 * 1.5).roundToInt().toString()
            binding.eightPacket.text = (485 / 2 * 1.5).roundToInt().toString()
            binding.ten.text = (625 * 1.5).roundToInt().toString()
            binding.tenPacket.text = (625 / 2 * 1.5).roundToInt().toString()
            binding.twelve.text = (800 * 1.5).roundToInt().toString()
            binding.twelvePacket.text = (800 / 4 * 1.5).roundToInt().toString()
            binding.sixteen.text = (1120 * 1.5).roundToInt().toString()
            binding.sixteenPacket.text = (1120 / 4 * 1.5).roundToInt().toString()
        }
        binding.buttonFive.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.one.text = (98 * 1.55).roundToInt().toString()
            binding.onePointFive.text = (128 * 1.55).roundToInt().toString()
            binding.two.text = (138 * 1.55).roundToInt().toString()
            binding.three.text = (177 * 1.55).roundToInt().toString()
            binding.four.text = (222 * 1.55).roundToInt().toString()
            binding.five.text = (274 * 1.55).roundToInt().toString()
            binding.six.text = (325 * 1.55).roundToInt().toString()
            binding.eight.text = (485 * 1.55).roundToInt().toString()
            binding.eightPacket.text = (485 / 2 * 1.55).roundToInt().toString()
            binding.ten.text = (625 * 1.55).roundToInt().toString()
            binding.tenPacket.text = (625 / 2 * 1.55).roundToInt().toString()
            binding.twelve.text = (800 * 1.55).roundToInt().toString()
            binding.twelvePacket.text = (800 / 4 * 1.55).roundToInt().toString()
            binding.sixteen.text = (1120 * 1.55).roundToInt().toString()
            binding.sixteenPacket.text = (1120 / 4 * 1.55).roundToInt().toString()
        }
        binding.buttonSix.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.one.text = (98 * 1.6).roundToInt().toString()
            binding.onePointFive.text = (128 * 1.6).roundToInt().toString()
            binding.two.text = (138 * 1.6).roundToInt().toString()
            binding.three.text = (177 * 1.6).roundToInt().toString()
            binding.four.text = (222 * 1.6).roundToInt().toString()
            binding.five.text = (274 * 1.6).roundToInt().toString()
            binding.six.text = (325 * 1.6).roundToInt().toString()
            binding.eight.text = (485 * 1.6).roundToInt().toString()
            binding.eightPacket.text = (485 / 2 * 1.6).roundToInt().toString()
            binding.ten.text = (625 * 1.6).roundToInt().toString()
            binding.tenPacket.text = (625 / 2 * 1.6).roundToInt().toString()
            binding.twelve.text = (800 * 1.6).roundToInt().toString()
            binding.twelvePacket.text = (800 / 4 * 1.6).roundToInt().toString()
            binding.sixteen.text = (1120 * 1.6).roundToInt().toString()
            binding.sixteenPacket.text = (1120 / 4 * 1.6).roundToInt().toString()
        }
        binding.buttonGo.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE

            var perc = binding.percentage.text.toString().toFloatOrNull()
            if (perc != null) {
                perc = perc / 100 + 1
                binding.one.text = (98 * perc).roundToInt().toString()
                binding.onePointFive.text = (128 * perc).roundToInt().toString()
                binding.two.text = (138 * perc).roundToInt().toString()
                binding.three.text = (177 * perc).roundToInt().toString()
                binding.four.text = (222 * perc).roundToInt().toString()
                binding.five.text = (274 * perc).roundToInt().toString()
                binding.six.text = (325 * perc).roundToInt().toString()
                binding.eight.text = (485 * perc).roundToInt().toString()
                binding.eightPacket.text = (485 / 2 * perc).roundToInt().toString()
                binding.ten.text = (625 * perc).roundToInt().toString()
                binding.tenPacket.text = (625 / 2 * perc).roundToInt().toString()
                binding.twelve.text = (800 * perc).roundToInt().toString()
                binding.twelvePacket.text = (800 / 4 * perc).roundToInt().toString()
                binding.sixteen.text = (1120 * perc).roundToInt().toString()
                binding.sixteenPacket.text = (1120 / 4 * perc).roundToInt().toString()
            }
        }


    }
}