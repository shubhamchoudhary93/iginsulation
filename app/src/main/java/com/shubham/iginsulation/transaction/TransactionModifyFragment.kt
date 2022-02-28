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
import com.shubham.iginsulation.BackupRestore
import com.shubham.iginsulation.R
import com.shubham.iginsulation.database.customer.CustomerDatabase
import com.shubham.iginsulation.database.customer.CustomerDatabaseDao
import com.shubham.iginsulation.database.transaction.Transaction
import com.shubham.iginsulation.database.transaction.TransactionDatabase
import com.shubham.iginsulation.database.transaction.TransactionDatabaseDao
import com.shubham.iginsulation.databinding.FragmentTransactionModifyBinding
import java.util.*
import kotlin.math.roundToInt

class TransactionModifyFragment : Fragment() {

    private lateinit var binding: FragmentTransactionModifyBinding
    private lateinit var transactionDatabase: TransactionDatabaseDao
    private lateinit var customerDatabase: CustomerDatabaseDao
    private lateinit var customers: List<String>

    private var id = 0L
    private var transaction = Transaction()
    private var oldReceipt = false
    private var oldAmount = 0F
    private var oldName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_transaction_modify, container, false
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
        binding.transactionModifyName.threshold = 1
        binding.transactionModifyName.setAdapter(adapterCategory)

        val args = TransactionModifyFragmentArgs.fromBundle(requireArguments())
        id = args.id

        fetchTransaction(id)

        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.transactionModify.setOnClickListener {

            val name = binding.transactionModifyName.text.toString()
            var amount = binding.transactionModifyAmount.text.toString()
            if (amount.toFloatOrNull() == null) {
                Toast.makeText(context, "amount should be numeric", Toast.LENGTH_SHORT).show()
                amount = ""
            }
            val date = binding.transactionModifyDate.text.toString()
            val detail = binding.transactionModifyDetail.text.toString()

            if (name != "" || customers.contains(name)) {
                modifyTransaction(
                    binding.receipt.isChecked,
                    name,
                    if (amount == "")
                        0
                    else
                        amount.toFloat().roundToInt(),
                    date,
                    detail
                )
                if(oldName == name) {
                    val oldCurrentBalance = customerDatabase.getCustomerCurrentBalance(name)
                    val newCurrentBalance = if (oldReceipt && binding.receipt.isChecked) {
                        oldCurrentBalance + oldAmount - amount.toFloat()
                    } else if (oldReceipt && !(binding.receipt.isChecked)) {
                        oldCurrentBalance + oldAmount + amount.toFloat()
                    } else if (!oldReceipt && binding.receipt.isChecked) {
                        oldCurrentBalance - oldAmount - amount.toFloat()
                    } else if (!oldReceipt && !(binding.receipt.isChecked)) {
                        oldCurrentBalance - oldAmount + amount.toFloat()
                    } else 0F
                    customerDatabase.setCustomerCurrentBalance(newCurrentBalance, name)
                } else{
                    val oldCurrentBalanceOldName = customerDatabase.getCustomerCurrentBalance(oldName)
                    val newCurrentBalanceOldName = if (oldReceipt) {
                        oldCurrentBalanceOldName + oldAmount
                    } else {
                        oldCurrentBalanceOldName - oldAmount
                    }
                    customerDatabase.setCustomerCurrentBalance(newCurrentBalanceOldName, oldName)

                    val oldCurrentBalanceNewName = customerDatabase.getCustomerCurrentBalance(name)
                    val newCurrentBalanceNewName = if (binding.receipt.isChecked) {
                        oldCurrentBalanceNewName - amount.toFloat()
                    } else {
                        oldCurrentBalanceNewName + amount.toFloat()
                    }
                    customerDatabase.setCustomerCurrentBalance(newCurrentBalanceNewName, name)
                }
            } else
                Toast.makeText(context, "customer doesn't exist", Toast.LENGTH_SHORT).show()
        }

        binding.transactionModifyDate.setOnClickListener {

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this.requireContext(),
                { _, yearPick, monthOfYear, dayOfMonth ->
                    val text = dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + yearPick
                    binding.transactionModifyDate.setText(text)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
    }

    private fun modifyTransaction(
        receipt: Boolean,
        name: String,
        amount: Int,
        date: String,
        detail: String
    ) {
        try {
            transactionDatabase.update(Transaction(id, receipt, name, amount, date, detail))
            BackupRestore.backup(context, "transaction")
            BackupRestore.backup(context, "customer")
        } catch (e: Exception) {
            e.printStackTrace()
        }

        view?.findNavController()
            ?.navigate(
                TransactionModifyFragmentDirections.actionTransactionModifyFragmentToTransactionDetailFragment(
                    id
                )
            )
    }

    private fun fetchTransaction(id: Long) {
        transaction = if (id == 0L) {
            try {
                transactionDatabase.getLastTransaction()
            } catch (e: Exception) {
                e.printStackTrace()
                Transaction()
            }
        } else {
            try {
                transactionDatabase.get(id)!!
            } catch (e: Exception) {
                e.printStackTrace()
                Transaction()
            }
        }
        setTransactionData(transaction)
    }

    private fun setTransactionData(transaction: Transaction) {
        binding.receipt.isChecked = transaction.receipt
        binding.transactionModifyName.setText(transaction.name)
        binding.transactionModifyAmount.setText(transaction.amount.toString())
        binding.transactionModifyDate.setText(transaction.date)
        binding.transactionModifyDetail.setText(transaction.detail)

        oldReceipt = transaction.receipt
        oldAmount = transaction.amount.toFloat()
        oldName = transaction.name
    }
}