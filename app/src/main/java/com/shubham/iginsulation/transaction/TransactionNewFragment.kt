package com.shubham.iginsulation.transaction

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.R
import com.shubham.iginsulation.database.customer.CustomerDatabase
import com.shubham.iginsulation.database.customer.CustomerDatabaseDao
import com.shubham.iginsulation.database.transaction.Transaction
import com.shubham.iginsulation.database.transaction.TransactionDatabase
import com.shubham.iginsulation.database.transaction.TransactionDatabaseDao
import com.shubham.iginsulation.databinding.FragmentTransactionNewBinding
import java.util.*
import kotlin.math.roundToInt

class TransactionNewFragment : Fragment() {

    private lateinit var binding: FragmentTransactionNewBinding
    private lateinit var transactionDatabase: TransactionDatabaseDao
    private lateinit var customerDatabase: CustomerDatabaseDao
    private lateinit var customers: List<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_transaction_new, container, false
        )

        transactionDatabase =
            TransactionDatabase.getInstance(requireContext()).transactionDatabaseDao
        customerDatabase = CustomerDatabase.getInstance(requireContext()).customerDatabaseDao

        customers = customerDatabase.getCustomerNames()
        val adapterCategory: ArrayAdapter<String> = ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_list_item_1,
            customers
        )
        binding.transactionNewName.threshold = 1
        binding.transactionNewName.setAdapter(adapterCategory)

        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.transactionNewAdd.setOnClickListener {

            val name = binding.transactionNewName.text.toString()
            var amount = binding.transactionNewAmount.text.toString()
            if (amount.toFloatOrNull() == null) {
                Toast.makeText(context, "amount should be numeric", Toast.LENGTH_SHORT).show()
                amount = ""
            }
            val date = binding.transactionNewDate.text.toString()
            val detail = binding.transactionNewDetail.text.toString()

            if (name != "" || customers.contains(name)) {
                insertTransaction(
                    binding.receipt.isChecked,
                    name,
                    if (amount == "")
                        0
                    else
                        amount.toFloat().roundToInt(),
                    date,
                    detail
                )
                val oldCurrentBalance = customerDatabase.getCustomerCurrentBalance(name)
                val newCurrentBalance = if (binding.receipt.isChecked) {
                    oldCurrentBalance - amount.toFloat()
                } else {
                    oldCurrentBalance + amount.toFloat()
                }
                customerDatabase.setCustomerCurrentBalance(newCurrentBalance, name)
            } else
                Toast.makeText(context, "customer doesn't exist", Toast.LENGTH_SHORT).show()
        }

        binding.transactionNewDate.setOnClickListener {

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this.requireContext(),
                { _, yearPick, monthOfYear, dayOfMonth ->
                    val text = dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + yearPick
                    binding.transactionNewDate.setText(text)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
    }

    private fun insertTransaction(
        receipt: Boolean,
        name: String,
        amount: Int,
        date: String,
        detail: String
    ) {
        try {
            transactionDatabase.insert(Transaction(0L, receipt, name, amount, date, detail))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        view?.findNavController()
            ?.navigate(
                TransactionNewFragmentDirections.actionTransactionNewFragmentToTransactionDetailFragment(
                    0L
                )
            )
    }
}