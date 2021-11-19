package com.shubham.iginsulation.customer

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
import com.shubham.iginsulation.databinding.FragmentCustomerModifyBinding
import kotlin.math.roundToInt

class CustomerModifyFragment : Fragment() {

    private lateinit var binding: FragmentCustomerModifyBinding
    private lateinit var customerDatabase: CustomerDatabaseDao

    private var id = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_customer_modify, container, false
        )

        customerDatabase = CustomerDatabase.getInstance(requireContext()).customerDatabaseDao

        val args = CustomerDetailFragmentArgs.fromBundle(requireArguments())
        id = args.id

        fetchCustomer(id)

        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.customerModify.setOnClickListener {

            val name = binding.customerModifyName.text.toString()
            var phone = binding.customerModifyPhone.text.toString()
            if (phone.toIntOrNull() == null) {
                Toast.makeText(context, "phone number should be numeric", Toast.LENGTH_SHORT).show()
                phone = ""
            }
            val address = binding.customerModifyAddress.text.toString()
            var openingBalance = binding.customerModifyOpening.text.toString()
            if (openingBalance.toFloatOrNull() == null) {
                Toast.makeText(context, "opening balance should be numeric", Toast.LENGTH_SHORT)
                    .show()
                openingBalance = ""
            }
            var currentBalance = binding.customerModifyCurrent.text.toString()
            if (currentBalance.toFloatOrNull() == null) {
                Toast.makeText(context, "closing balance should be numeric", Toast.LENGTH_SHORT)
                    .show()
                currentBalance = ""
            }

            if (name != "") {
                modifyCustomer(
                    name,
                    if (phone == "")
                        0L
                    else
                        phone.toLong(),
                    address,
                    if (openingBalance == "")
                        0F
                    else
                        openingBalance.toFloat(),
                    if (currentBalance == "")
                        0F
                    else
                        currentBalance.toFloat()
                )
            } else {
                Toast.makeText(this.context, "Name is empty", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun modifyCustomer(
        name: String,
        phone: Long,
        address: String,
        openingBalance: Float,
        currentBalance: Float
    ) {
        val customer = Customer()
        customer.id = id
        customer.name = name
        customer.phone = phone
        customer.address = address
        customer.opening = openingBalance
        customer.current = currentBalance

        try {
            customerDatabase.update(customer)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        view?.findNavController()?.navigate(
            CustomerModifyFragmentDirections.actionCustomerModifyFragmentToCustomerDetailFragment(
                id
            )
        )
    }

    private fun fetchCustomer(id: Long) {
        val customer = if (id == 0L) {
            try {
                customerDatabase.getLastCustomer()
            } catch (e: Exception) {
                e.printStackTrace()
                Customer()
            }
        } else {
            try {
                customerDatabase.get(id)!!
            } catch (e: Exception) {
                e.printStackTrace()
                Customer()
            }
        }

        setCustomerData(customer)
    }

    private fun setCustomerData(customer: Customer) {
        binding.customerModifyName.text = customer.name
        binding.customerModifyPhone.setText(customer.phone.toString())
        binding.customerModifyAddress.setText(customer.address)
        binding.customerModifyOpening.setText(customer.opening.toString())
        binding.customerModifyCurrent.setText(customer.current.toString())
    }
}