package com.shubham.iginsulation.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.BackupRestore
import com.shubham.iginsulation.R
import com.shubham.iginsulation.database.customer.Customer
import com.shubham.iginsulation.database.customer.CustomerDatabase
import com.shubham.iginsulation.database.customer.CustomerDatabaseDao
import com.shubham.iginsulation.databinding.FragmentCustomerNewBinding
import kotlin.math.roundToInt

class CustomerNewFragment : Fragment() {

    private lateinit var binding: FragmentCustomerNewBinding
    private lateinit var customerDatabase: CustomerDatabaseDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_customer_new, container, false
        )

        customerDatabase = CustomerDatabase.getInstance(requireContext()).customerDatabaseDao

        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.customerNewAdd.setOnClickListener {

            val name = binding.customerNewName.text.toString()
            var phone = binding.customerNewPhone.text.toString()
            if (phone.toIntOrNull() == null) {
                Toast.makeText(context, "phone number should be numeric", Toast.LENGTH_SHORT).show()
                phone = ""
            }
            val address = binding.customerNewAddress.text.toString()
            var openingBalance = binding.customerNewOpening.text.toString()
            if (openingBalance.toFloatOrNull() == null) {
                Toast.makeText(context, "opening balance should be numeric", Toast.LENGTH_SHORT).show()
                openingBalance = ""
            }
            var currentBalance = binding.customerNewCurrent.text.toString()
            if (currentBalance.toFloatOrNull() == null){
                Toast.makeText(context, "closing balance should be numeric", Toast.LENGTH_SHORT).show()
                currentBalance = ""
            }

            if (name != "") {
                insertCustomer(
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

    private fun insertCustomer(
        name: String,
        phone: Long,
        address: String,
        openingBalance: Float,
        currentBalance: Float
    ) {
        if (customerDatabase.checkCustomer(name).isEmpty()) {
            val customer = Customer()
            customer.name = name
            customer.phone = phone
            customer.address = address
            customer.opening = openingBalance
            customer.current = currentBalance

            try {
                customerDatabase.insert(customer)
                BackupRestore.backup(context, "customer")
            } catch (e: Exception) {
                e.printStackTrace()
            }
            view?.findNavController()?.navigate(
                CustomerNewFragmentDirections.actionCustomerNewFragmentToCustomerDetailFragment(
                    0L
                )
            )
        } else
            Toast.makeText(context, "customer already exist", Toast.LENGTH_SHORT).show()
    }
}