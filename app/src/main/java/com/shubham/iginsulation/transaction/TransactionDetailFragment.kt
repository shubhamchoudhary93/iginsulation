package com.shubham.iginsulation.transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.shubham.iginsulation.databinding.FragmentTransactionDetailBinding

class TransactionDetailFragment : Fragment() {

    private lateinit var binding: FragmentTransactionDetailBinding
    private lateinit var transactionDatabase: TransactionDatabaseDao
    private lateinit var customerDatabase: CustomerDatabaseDao

    private var id = 0L
    private var transaction = Transaction()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_transaction_detail, container, false
        )

        transactionDatabase =
            TransactionDatabase.getInstance(requireContext()).transactionDatabaseDao

        customerDatabase =
            CustomerDatabase.getInstance(requireContext()).customerDatabaseDao

        val args = TransactionDetailFragmentArgs.fromBundle(requireArguments())
        id = args.id

        fetchTransaction(id)

        binding.transactionDetailDelete.setOnClickListener {
            transactionDatabase.delete(id)
            val oldCurrentBalance =
                customerDatabase.getCustomerCurrentBalance(transaction.name)
            val newBalanceO =
                if (transaction.receipt) oldCurrentBalance + transaction.amount
                else oldCurrentBalance - transaction.amount
            customerDatabase.setCustomerCurrentBalance(
                newBalanceO,
                transaction.name
            )

            BackupRestore.backup(context, "transaction")
            BackupRestore.backup(context, "customer")
            view?.findNavController()?.navigate(
                TransactionDetailFragmentDirections.actionTransactionDetailFragmentToTransactionListFragment()
            )
        }

        binding.transactionDetailModify.setOnClickListener {
            view?.findNavController()?.navigate(
                TransactionDetailFragmentDirections.actionTransactionDetailFragmentToTransactionModifyFragment(id)
            )
        }

        return binding.root
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
        binding.receipt.text = transaction.receipt.toString()
        binding.transactionDetailName.text = transaction.name
        binding.transactionDetailAmount.text = transaction.amount.toString()
        binding.transactionDetailDate.text = transaction.date
        binding.transactionDetailDetail.text = transaction.detail
    }
}