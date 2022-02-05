package com.shubham.iginsulation.committee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.shubham.iginsulation.R
import com.shubham.iginsulation.databinding.FragmentCommitteeCalculatorBinding

class CommitteeCalculator : Fragment() {

    private lateinit var binding: FragmentCommitteeCalculatorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_committee_calculator, container, false
        )

        setListeners()
        binding.committeeCalculatedBidText.visibility = View.GONE
        binding.committeeCalculatedInstallmentText.visibility = View.GONE
        binding.committeeCalculatedInterestText.visibility = View.GONE
        return binding.root
    }

    private fun setListeners() {
        binding.committeeCalculateInstallment.setOnClickListener {

            val committeeIdealInstallment =
                binding.committeeIdealInstallment.editText?.text.toString().toFloatOrNull()
            val committeeTotalMember =
                binding.committeeTotalMember.editText?.text.toString().toFloatOrNull()
            val committeeCurrentMonth =
                binding.committeeCurrentMonth.editText?.text.toString().toFloatOrNull()

            val committeeBid = binding.committeeBid.editText?.text.toString().toFloatOrNull()

            var calculatedInstallment = 0F
            var calculatedInterest = 0F
            if (committeeIdealInstallment != null && committeeTotalMember != null && committeeCurrentMonth != null && committeeBid != null) {
                calculatedInstallment =
                    ((committeeIdealInstallment * committeeTotalMember) - committeeBid) / committeeTotalMember
                calculatedInterest =
                    (100 * committeeBid) / ((committeeTotalMember * calculatedInstallment) * (committeeTotalMember - committeeCurrentMonth))
            }

            binding.committeeCalculatedInstallmentText.visibility = View.GONE
            binding.committeeCalculatedInterestText.visibility = View.GONE
            binding.committeeCalculatedInstallment.text = calculatedInstallment.toString()
            binding.committeeCalculatedInterest.text = calculatedInterest.toString()
        }

        binding.committeeCalculateBid.setOnClickListener {
            val committeeIdealInstallment =
                binding.committeeIdealInstallment.editText?.text.toString().toFloatOrNull()
            val committeeTotalMember =
                binding.committeeTotalMember.editText?.text.toString().toFloatOrNull()
            val committeeCurrentMonth =
                binding.committeeCurrentMonth.editText?.text.toString().toFloatOrNull()

            val projectedInterest =
                binding.committeeProjectedInterest.editText?.text.toString().toFloatOrNull()

            var calculatedBid = 0F
            if (committeeIdealInstallment != null && committeeTotalMember != null && committeeCurrentMonth != null && projectedInterest != null) {
                calculatedBid =
                    (committeeIdealInstallment * committeeTotalMember * projectedInterest * (committeeTotalMember - committeeCurrentMonth)) / (100 + (projectedInterest * (committeeTotalMember - committeeCurrentMonth)))
            }

            binding.committeeCalculatedBidText.visibility = View.GONE
            binding.committeeCalculatedBid.text = calculatedBid.toString()
        }
    }
}
