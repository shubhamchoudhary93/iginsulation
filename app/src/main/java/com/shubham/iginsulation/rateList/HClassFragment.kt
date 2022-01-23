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
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.shubham.iginsulation.IDAssign
import com.shubham.iginsulation.R
import com.shubham.iginsulation.databinding.FragmentHClassBinding
import kotlin.math.roundToInt

class HClassFragment : Fragment() {

    private lateinit var binding: FragmentHClassBinding

    private var buttonOneValue = listOf(
        60F,
        60F,
        65F,
        95F,
        95F,
        110F,
        110F,
        110F,
        120F,
        120F,
        120F
    )

    private var buttonTwoValue = listOf(
        65F,
        80F,
        70F,
        100F,
        100F,
        120F,
        120F,
        120F,
        130F,
        130F,
        130F
    )

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
            R.layout.fragment_h_class, container, false
        )

        IDAssign.assign(binding)
        setListeners()

        binding.valuesLayout.visibility = View.GONE

        for (i in 1..11) {
            binding.root.findViewById<Button>(50000 + i).text = buttonOneValue[i - 1].toString()
            binding.root.findViewById<Button>(60000 + i).text = buttonTwoValue[i - 1].toString()
        }
        return binding.root
    }

    private fun setListeners() {
        val percentage = buttonTwoValue.toMutableList()

        for (i in 1..11) {
            binding.root.findViewById<Button>(50000 + i).setOnClickListener {
                percentage[i - 1] = buttonOneValue[i - 1]
                binding.root.findViewById<Button>(50000 + i).setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.button_select
                    )
                )
                binding.root.findViewById<Button>(60000 + i).setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )
            }

            binding.root.findViewById<Button>(60000 + i).setOnClickListener {
                percentage[i - 1] = buttonTwoValue[i - 1]
                binding.root.findViewById<Button>(60000 + i).setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.button_select
                    )
                )
                binding.root.findViewById<Button>(50000 + i).setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )
            }
        }

        binding.buttonGo.setOnClickListener {

            for (i in 1..11) {
                if (binding.root.findViewById<TextView>(70000 + i).text.toString() != "")
                    percentage[i - 1] =
                        binding.root.findViewById<TextView>(70000 + i).text.toString().toFloat()
            }

            binding.percentageLayout.visibility = View.GONE
            binding.valuesLayout.visibility = View.VISIBLE

            var k = 0
            for (i in 1..15) {
                if (i == 9 || i == 11) {
                    binding.root.findViewById<TextView>(40000 + i).text =
                        (rateList[k] / 2 * (1 + (percentage[k] / 100))).roundToInt().toString()
                                k ++
                } else if (i == 13 || i == 15) {
                    binding.root.findViewById<TextView>(40000 + i).text =
                        (rateList[k] / 4 * (1 + (percentage[k] / 100))).roundToInt().toString()
                    k++
                } else if (i == 8 || i == 10 || i == 12 || i == 14) {
                    binding.root.findViewById<TextView>(40000 + i).text =
                        (rateList[k] * (1 + (percentage[k] / 100))).roundToInt().toString()
                } else {
                    binding.root.findViewById<TextView>(40000 + i).text =
                        (rateList[k] * (1 + (percentage[k] / 100))).roundToInt().toString()
                    k++
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