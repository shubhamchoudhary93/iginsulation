package com.shubham.iginsulation.committee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.R
import com.shubham.iginsulation.database.customer.Customer
import com.shubham.iginsulation.database.customer.CustomerDatabase
import com.shubham.iginsulation.database.customer.CustomerDatabaseDao
import com.shubham.iginsulation.databinding.FragmentCommitteeCalculatorBinding
import com.shubham.iginsulation.databinding.FragmentCustomerNewBinding
import kotlin.math.roundToInt

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
        return binding.root
    }

    private fun setListeners() {
        binding.committeeCalculateInstallment.setOnClickListener {

            val committeeIdealInstallment =
                binding.committeeIdealInstallment.text.toString().toFloatOrNull()
            val committeeTotalMember = binding.committeeTotalMember.text.toString().toFloatOrNull()
            val committeeCurrentMonth =
                binding.committeeCurrentMonth.text.toString().toFloatOrNull()

            val committeeBid = binding.committeeBid.text.toString().toFloatOrNull()

            var calculatedInstallment = 0F
            var calculatedInterest = 0F
            if (committeeIdealInstallment != null && committeeTotalMember != null && committeeCurrentMonth != null && committeeBid != null) {
                calculatedInstallment =
                    ((committeeIdealInstallment * committeeTotalMember) - committeeBid) / committeeTotalMember
                calculatedInterest =
                    (100 * committeeBid) / ((committeeTotalMember * calculatedInstallment) * (committeeTotalMember - committeeCurrentMonth))
            }

            binding.committeeCalculatedInstallment.text = calculatedInstallment.toString()
            binding.committeeCalculatedInterest.text = calculatedInterest.toString()
        }

        binding.committeeCalculateBid.setOnClickListener {
            val committeeIdealInstallment =
                binding.committeeIdealInstallment.text.toString().toFloatOrNull()
            val committeeTotalMember = binding.committeeTotalMember.text.toString().toFloatOrNull()
            val committeeCurrentMonth =
                binding.committeeCurrentMonth.text.toString().toFloatOrNull()

            val projectedInterest =
                binding.committeeProjectedInterest.text.toString().toFloatOrNull()

            var calculatedBid = 0F
            if (committeeIdealInstallment != null && committeeTotalMember != null && committeeCurrentMonth != null && projectedInterest != null) {
                calculatedBid =
                    (committeeIdealInstallment * committeeTotalMember * projectedInterest * (committeeTotalMember - committeeCurrentMonth)) / (100 + (projectedInterest * (committeeTotalMember - committeeCurrentMonth)))
            }

            binding.committeeCalculatedBid.text = calculatedBid.toString()
        }
    }
}
