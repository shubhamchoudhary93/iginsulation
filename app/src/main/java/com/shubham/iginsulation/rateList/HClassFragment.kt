package com.shubham.iginsulation.rateList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.shubham.iginsulation.R
import com.shubham.iginsulation.databinding.FragmentHClassBinding
import kotlin.math.roundToInt

class HClassFragment : Fragment() {

    private lateinit var binding: FragmentHClassBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_h_class, container, false
        )

        setListeners()

        binding.valuesLayout.visibility = View.GONE
        return binding.root
    }

    private fun setListeners() {
        var percOne = 1.6F
        var percOnePointFive = 1.8F
        var percTwo = 1.6F
        var percThree = 2F
        var percFour = 2F
        var percFive = 2.2F
        var percSix = 2.2F
        var percEight = 2.2F
        var percTen = 2.3F
        var percTwelve = 2.3F
        var percSixteen = 2.3F

        binding.buttonOneOne.setOnClickListener {
            percOne = 1.55F
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
        }
        binding.buttonOneTwo.setOnClickListener {
            percOne = 1.6F
            binding.buttonOneTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonOneOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }
        binding.buttonOnePointFiveOne.setOnClickListener {
            percOnePointFive = 1.6F
            binding.buttonOnePointFiveOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonOnePointFiveTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }
        binding.buttonOnePointFiveTwo.setOnClickListener {
            percOnePointFive = 1.8F
            binding.buttonOnePointFiveTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonOnePointFiveOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }
        binding.buttonTwoOne.setOnClickListener {
            percTwo = 1.55F
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
            percTwo = 1.6F
            binding.buttonTwoTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonTwoOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }
        binding.buttonThreeOne.setOnClickListener {
            percThree = 1.95F
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
            percThree = 2F
            binding.buttonThreeTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonThreeOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }
        binding.buttonFourOne.setOnClickListener {
            percFour = 1.95F
            binding.buttonFourOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonFourTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }
        binding.buttonFourTwo.setOnClickListener {
            percFour = 2F
            binding.buttonFourTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonFourOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }
        binding.buttonFiveOne.setOnClickListener {
            percFive = 2.1F
            binding.buttonFiveOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonFiveTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }
        binding.buttonFiveTwo.setOnClickListener {
            percFive = 2.2F
            binding.buttonFiveTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonFiveOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }
        binding.buttonSixOne.setOnClickListener {
            percSix = 2.1F
            binding.buttonSixOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonSixTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }
        binding.buttonSixTwo.setOnClickListener {
            percSix = 2.2F
            binding.buttonSixTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonSixOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }
        binding.buttonEightOne.setOnClickListener {
            percEight = 2.1F
            binding.buttonEightOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonEightTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }
        binding.buttonEightTwo.setOnClickListener {
            percEight = 2.1F
            binding.buttonEightTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonEightOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }
        binding.buttonTenOne.setOnClickListener {
            percTen = 2.2F
            binding.buttonTenOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonTenTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }
        binding.buttonTenTwo.setOnClickListener {
            percTen = 2.3F
            binding.buttonTenTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonTenOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }
        binding.buttonTwelveOne.setOnClickListener {
            percTwelve = 2.2F
            binding.buttonTwelveOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonTwelveTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }
        binding.buttonTwelveTwo.setOnClickListener {
            percTwelve = 2.3F
            binding.buttonTwelveTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonTwelveOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }
        binding.buttonSixteenOne.setOnClickListener {
            percSixteen = 2.2F
            binding.buttonSixteenOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonSixteenTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }
        binding.buttonSixteenTwo.setOnClickListener {
            percSixteen = 2.3F
            binding.buttonSixteenTwo.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.button_select
                )
            )
            binding.buttonSixteenOne.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
        }

        binding.buttonGo.setOnClickListener {

            if(binding.percentageOne.text.toString() != "")
                percOne = binding.percentageOne.text.toString().toFloat()
            if(binding.percentageOnePointFive.text.toString() != "")
                percOnePointFive = binding.percentageOnePointFive.text.toString().toFloat()
            if(binding.percentageTwo.text.toString() != "")
                percTwo = binding.percentageTwo.text.toString().toFloat()
            if(binding.percentageThree.text.toString() != "")
                percThree = binding.percentageThree.text.toString().toFloat()
            if(binding.percentageFour.text.toString() != "")
                percFour = binding.percentageFour.text.toString().toFloat()
            if(binding.percentageFive.text.toString() != "")
                percFive = binding.percentageFive.text.toString().toFloat()
            if(binding.percentageSix.text.toString() != "")
                percSix = binding.percentageSix.text.toString().toFloat()
            if(binding.percentageEight.text.toString() != "")
                percEight = binding.percentageEight.text.toString().toFloat()
            if(binding.percentageTen.text.toString() != "")
                percTen = binding.percentageTen.text.toString().toFloat()
            if(binding.percentageTwelve.text.toString() != "")
                percTwelve = binding.percentageTwelve.text.toString().toFloat()
            if(binding.percentageSixteen.text.toString() != "")
                percSixteen = binding.percentageSixteen.text.toString().toFloat()
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE
            binding.one.text = (98 * percOne).roundToInt().toString()
            binding.onePointFive.text = (128 * percOnePointFive).roundToInt().toString()
            binding.two.text = (138 * percTwo).roundToInt().toString()
            binding.three.text = (177 * percThree).roundToInt().toString()
            binding.four.text = (222 * percFour).roundToInt().toString()
            binding.five.text = (274 * percFive).roundToInt().toString()
            binding.six.text = (325 * percSix).roundToInt().toString()
            binding.eight.text = (485 * percEight).roundToInt().toString()
            binding.eightPacket.text = (485 / 2 * percEight).roundToInt().toString()
            binding.ten.text = (625 * percTen).roundToInt().toString()
            binding.tenPacket.text = (625 / 2 * percTen).roundToInt().toString()
            binding.twelve.text = (800 * percTwelve).roundToInt().toString()
            binding.twelvePacket.text = (800 / 4 * percTwelve).roundToInt().toString()
            binding.sixteen.text = (1120 * percSixteen).roundToInt().toString()
            binding.sixteenPacket.text = (1120 / 4 * percSixteen).roundToInt().toString()
        }
    }
}