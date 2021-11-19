package com.shubham.iginsulation.rateList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.shubham.iginsulation.R
import com.shubham.iginsulation.databinding.FragmentChinaBinding
import kotlin.math.roundToInt

class ChinaFragment : Fragment() {

    private lateinit var binding: FragmentChinaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_china, container, false
        )

        setListeners()

        binding.valuesLayout.visibility = View.GONE
        return binding.root
    }

    private fun setListeners() {
        var percOne = 0.65F
        var percTwo = 0.78F
        var percThree = 1.4F

        binding.buttonOneOne.setOnClickListener {
            percOne = 0.8F
            binding.buttonOneOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonOneTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneThree.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneFour.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneFive.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneSix.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneSeven.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneEight.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }

        binding.buttonOneTwo.setOnClickListener {
            percOne = 0.75F
            binding.buttonOneOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonOneThree.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneFour.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneFive.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneSix.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneSeven.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneEight.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }

        binding.buttonOneThree.setOnClickListener {
            percOne = 0.7F
            binding.buttonOneOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneThree.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonOneFour.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneFive.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneSix.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneSeven.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneEight.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }

        binding.buttonOneFour.setOnClickListener {
            percOne = 0.65F
            binding.buttonOneOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneThree.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneFour.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonOneFive.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneSix.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneSeven.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneEight.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }

        binding.buttonOneFive.setOnClickListener {
            percOne = 0.63F
            binding.buttonOneOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneThree.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneFour.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneFive.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonOneSix.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneSeven.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneEight.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }

        binding.buttonOneSix.setOnClickListener {
            percOne = 0.62F
            binding.buttonOneOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneThree.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneFour.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneFive.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneSix.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonOneSeven.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneEight.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }

        binding.buttonOneSeven.setOnClickListener {
            percOne = 0.61F
            binding.buttonOneOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneThree.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneFour.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneFive.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneSix.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneSeven.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonOneEight.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }

        binding.buttonOneEight.setOnClickListener {
            percOne = 0.6F
            binding.buttonOneOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneThree.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneFour.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneFive.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneSix.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneSeven.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonOneEight.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
        }

        binding.buttonTwoOne.setOnClickListener {
            percOne = 0.78F
            binding.buttonTwoOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonTwoTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }

        binding.buttonTwoTwo.setOnClickListener {
            percOne = 0.75F
            binding.buttonTwoOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonTwoTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
        }

        binding.buttonThreeOne.setOnClickListener {
            percOne = 1.35F
            binding.buttonThreeOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonThreeTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }

        binding.buttonThreeTwo.setOnClickListener {
            percOne = 1.4F
            binding.buttonThreeOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.buttonThreeTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
        }

        binding.buttonGo.setOnClickListener {

            if (binding.percentageOne.text.toString() != "")
                percOne = binding.percentageOne.text.toString().toFloat()
            if (binding.percentageTwo.text.toString() != "")
                percTwo = binding.percentageTwo.text.toString().toFloat()
            if (binding.percentageThree.text.toString() != "")
                percThree = binding.percentageThree.text.toString().toFloat()
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.one.text = (150 * percOne).roundToInt().toString()
            binding.onePointFive.text = (200 * percOne).roundToInt().toString()
            binding.two.text = (200 * percOne).roundToInt().toString()
            binding.three.text = (300 * percOne).roundToInt().toString()
            binding.four.text = (400 * percOne).roundToInt().toString()
            binding.five.text = (500 * percOne).roundToInt().toString()
            binding.six.text = (600 * percOne).roundToInt().toString()
            binding.eight.text = (800 * percOne).roundToInt().toString()
            binding.eightPacket.text = (800 / 2 * percOne).roundToInt().toString()
            binding.ten.text = (1000 * percTwo).roundToInt().toString()
            binding.tenPacket.text = (1000 / 2 * percTwo).roundToInt().toString()
            binding.twelve.text = (1200 * percTwo).roundToInt().toString()
            binding.twelvePacket.text = (1200 / 2 * percTwo).roundToInt().toString()
            binding.sixteen.text = (1600 * percThree).roundToInt().toString()
            binding.sixteenPacket.text = (1600 / 2 * percThree).roundToInt().toString()
        }
    }
}