package com.shubham.iginsulation.rateList

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.shubham.iginsulation.IDAssign
import com.shubham.iginsulation.R
import com.shubham.iginsulation.databinding.FragmentFClassBinding
import kotlin.math.roundToInt

class FClassFragment : Fragment() {

    private lateinit var binding: FragmentFClassBinding

    private var buttonValue = listOf(
        45F,
        50F,
        55F,
        58F,
        60F,
        65F
    )
    private var buttonOneValue = 45F
    private var buttonTwoValue = 50F
    private var buttonThreeValue = 55F
    private var buttonFourValue = 58F
    private var buttonFiveValue = 60F
    private var buttonSixValue = 65F

    private var rateList = listOf(
        98F,
        128F,
        138F,
        177F,
        222F,
        274F,
        325F,
        485F,
        625F,
        800F,
        1390F
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_f_class, container, false
        )
        IDAssign.assign(binding)
        setListeners()

        binding.valuesLayout.visibility = View.GONE

        for (i in 1..6) {
            binding.root.findViewById<Button>(50000 + i).text = buttonValue[i - 1].toString()
        }

        return binding.root
    }

    private fun setListeners() {

        for (j in 1..6) {
            binding.root.findViewById<Button>(50000 + j).setOnClickListener {
                binding.percentageLayout.visibility = View.GONE
                binding.valuesLayout.visibility = View.VISIBLE
                val percentage = buttonValue[j - 1]
                binding.headRate.text = percentage.toString()
                var k = 0
                for (i in 1..15) {
                    if (i == 9 || i == 11) {
                        binding.root.findViewById<TextView>(40000 + i).text =
                            (rateList[k] / 2 * (1 + (percentage / 100))).roundToInt().toString()
                        k++
                    } else if (i == 13 || i == 15) {
                        binding.root.findViewById<TextView>(40000 + i).text =
                            (rateList[k] / 4 * (1 + (percentage / 100))).roundToInt().toString()
                        k++
                    } else if (i == 8 || i == 10 || i == 12 || i == 14) {
                        binding.root.findViewById<TextView>(40000 + i).text =
                            (rateList[k] * (1 + (percentage / 100))).roundToInt().toString()
                    } else {
                        binding.root.findViewById<TextView>(40000 + i).text =
                            (rateList[k] * (1 + (percentage / 100))).roundToInt().toString()
                        k++
                    }
                }
            }
        }

        binding.buttonGo.setOnClickListener {
            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE

            val percentage = binding.percentage.text.toString().toFloatOrNull()
            binding.headRate.text = percentage.toString()
            if (percentage != null) {
                var k = 0
                for (i in 1..15) {
                    if (i == 9 || i == 11) {
                        binding.root.findViewById<TextView>(40000 + i).text =
                            (rateList[k] / 2 * (1 + (percentage / 100))).roundToInt().toString()
                        k++
                    } else if (i == 13 || i == 15) {
                        binding.root.findViewById<TextView>(40000 + i).text =
                            (rateList[k] / 4 * (1 + (percentage / 100))).roundToInt().toString()
                        k++
                    } else if (i == 8 || i == 10 || i == 12 || i == 14) {
                        binding.root.findViewById<TextView>(40000 + i).text =
                            (rateList[k] * (1 + (percentage / 100))).roundToInt().toString()
                    } else {
                        binding.root.findViewById<TextView>(40000 + i).text =
                            (rateList[k] * (1 + (percentage / 100))).roundToInt().toString()
                        k++
                    }
                }
            }
        }

        binding.copyText.setOnClickListener {
            var string = "China rate list :\n"
            val mm = listOf(
                1,1.5,2,3,4,5,6,8,10,12,16
            )
            for (i in 1..11) {
                string = string + mm[i-1] + "mm - " + binding.root.findViewById<TextView>(40000 + i).text.toString()+"\n"
            }
            string += "per 100mtr"

            val clipboardManager = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val myClip: ClipData = ClipData.newPlainText("iginsulation", string)
            clipboardManager.setPrimaryClip(myClip)

        }
    }
}