package com.shubham.iginsulation.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.BackupRestore
import com.shubham.iginsulation.R
import com.shubham.iginsulation.database.customer.Customer
import com.shubham.iginsulation.database.customer.CustomerDatabase
import com.shubham.iginsulation.database.customer.CustomerDatabaseDao
import com.shubham.iginsulation.databinding.FragmentCustomerDetailBinding
import com.shubham.iginsulation.sale.SaleDetailFragmentDirections
import kotlin.math.roundToInt

class CustomerDetailFragment : Fragment() {

    private lateinit var binding: FragmentCustomerDetailBinding
    private lateinit var customerDatabase: CustomerDatabaseDao

    private var id = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_customer_detail, container, false
        )

        customerDatabase = CustomerDatabase.getInstance(requireContext()).customerDatabaseDao

        val args = CustomerDetailFragmentArgs.fromBundle(requireArguments())
        id = args.id

        fetchCustomer(id)

        binding.customerDetailModify.setOnClickListener {
            view?.findNavController()?.navigate(
                CustomerDetailFragmentDirections.actionCustomerDetailFragmentToCustomerModifyFragment(id)
            )
        }
        binding.customerDetailDelete.setOnClickListener {
            customerDatabase.delete(id)
            BackupRestore.backup(context, "customer")

            view?.findNavController()?.navigate(
                CustomerDetailFragmentDirections.actionCustomerDetailFragmentToCustomerListFragment()
            )
        }

        return binding.root
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
        binding.customerDetailName.text = customer.name
        binding.customerDetailPhone.text = customer.phone.toString()
        binding.customerDetailAddress.text = customer.address
        binding.customerDetailOpening.text = customer.opening.roundToInt().toString()
        binding.customerDetailCurrent.text = customer.current.roundToInt().toString()
    }
}