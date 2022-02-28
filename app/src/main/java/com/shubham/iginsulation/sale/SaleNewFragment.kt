package com.shubham.iginsulation.sale

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.BackupRestore
import com.shubham.iginsulation.IDAssign
import com.shubham.iginsulation.R
import com.shubham.iginsulation.database.customer.CustomerDatabase
import com.shubham.iginsulation.database.customer.CustomerDatabaseDao
import com.shubham.iginsulation.database.sale.Sale
import com.shubham.iginsulation.database.sale.SaleDatabase
import com.shubham.iginsulation.database.sale.SaleDatabaseDao
import com.shubham.iginsulation.database.saledetails.SaleDetails
import com.shubham.iginsulation.database.saledetails.SaleDetailsDatabase
import com.shubham.iginsulation.database.saledetails.SaleDetailsDatabaseDao
import com.shubham.iginsulation.database.stock.StockDatabase
import com.shubham.iginsulation.database.stock.StockDatabaseDao
import com.shubham.iginsulation.database.transaction.Transaction
import com.shubham.iginsulation.database.transaction.TransactionDatabase
import com.shubham.iginsulation.database.transaction.TransactionDatabaseDao
import com.shubham.iginsulation.databinding.FragmentSaleNewBinding
import java.util.*
import kotlin.math.roundToInt

class SaleNewFragment : Fragment() {

    private lateinit var binding: FragmentSaleNewBinding
    private lateinit var saleDatabase: SaleDatabaseDao
    private lateinit var saleDetailsDatabase: SaleDetailsDatabaseDao
    private lateinit var customerDatabase: CustomerDatabaseDao
    private lateinit var transactionDatabase: TransactionDatabaseDao
    private lateinit var customers: List<String>
    private lateinit var stockDatabase: StockDatabaseDao
    private lateinit var stocks: List<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_sale_new, container, false
        )

        saleDatabase = SaleDatabase.getInstance(requireContext()).saleDatabaseDao
        saleDetailsDatabase = SaleDetailsDatabase.getInstance(requireContext()).saleDetailsDatabaseDao
        customerDatabase = CustomerDatabase.getInstance(requireContext()).customerDatabaseDao
        stockDatabase = StockDatabase.getInstance(requireContext()).stockDatabaseDao
        transactionDatabase = TransactionDatabase.getInstance(requireContext()).transactionDatabaseDao

        customers = customerDatabase.getCustomerNames()
        val adapterCustomer: ArrayAdapter<String> = ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_list_item_1,
            customers
        )
        binding.saleNewName.threshold = 1
        binding.saleNewName.setAdapter(adapterCustomer)

        stocks = stockDatabase.getStockNames()
        val adapterStock: ArrayAdapter<String> = ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_list_item_1,
            stocks
        )

        IDAssign.assign(binding)

        for (i in 20001..20020) {
            binding.root.findViewById<AutoCompleteTextView>(i).threshold = 1
            binding.root.findViewById<AutoCompleteTextView>(i).setAdapter(adapterStock)
            binding.root.findViewById<AutoCompleteTextView>(i).setOnItemClickListener { parent, _, position, _ ->
                val selectedItem = parent.getItemAtPosition(position).toString()
                val stockDetailReturn = stockDatabase.get(selectedItem)
                if (stockDetailReturn != null) {
                    if (stockDetailReturn.rate != 0F) {
                        val j = i + 100
                        binding.root.findViewById<EditText>(j).setText(stockDetailReturn.rate.toString())
                    }
                    if (stockDetailReturn.percentage != 0F) {
                        val j = i + 200
                        binding.root.findViewById<EditText>(j).setText(stockDetailReturn.percentage.toString())
                    }
                }
            }
        }

        binding.saleNewAdd.setOnClickListener {
            if (binding.saleNewName.text.toString() != "" && customers.contains(binding.saleNewName.text.toString())) {
                val sale = Sale()
                sale.cash = binding.cash.isChecked
                if (binding.saleNewName.text.toString() != "")
                    sale.name = binding.saleNewName.text.toString()
                if (binding.saleNewDate.text.toString() != "")
                    sale.date = binding.saleNewDate.text.toString()
                if (binding.saleNewTransport.text.toString() != "")
                    sale.transport = binding.saleNewTransport.text.toString().toFloatOrNull() ?: 0F
                if (binding.saleNewOther.text.toString() != "")
                    sale.otherCharges = binding.saleNewOther.text.toString().toFloatOrNull() ?: 0F

                saleDatabase.insert(sale)

                var total = 0
                val id = saleDatabase.getLastSaleID()

                for (i in 20001..20020) {
                    val j = i + 100
                    val k = j + 100
                    val l = k + 100
                    if (binding.root.findViewById<AutoCompleteTextView>(i).text.toString() != "") {
                        val saleDetails = SaleDetails()
                        saleDetails.customer = sale.name
                        saleDetails.saleId = id
                        saleDetails.item = binding.root.findViewById<AutoCompleteTextView>(i).text.toString()
                        saleDetails.quantity = binding.root.findViewById<EditText>(l).text.toString().toFloatOrNull() ?: 0F
                        saleDetails.rate = binding.root.findViewById<EditText>(j).text.toString().toFloatOrNull() ?: 0F
                        saleDetails.percentage = binding.root.findViewById<EditText>(k).text.toString().toFloatOrNull() ?: 0F
                        saleDetails.total = (saleDetails.quantity * saleDetails.rate * saleDetails.percentage).roundToInt()
                        total += saleDetails.total
                        saleDetailsDatabase.insert(saleDetails)
                    }
                }

                total = (total + sale.transport + sale.otherCharges).toInt()

                sale.id = id
                sale.amount = total
                saleDatabase.update(sale)
                if (sale.cash) {
                    try {
                        transactionDatabase.insert(Transaction(0L, true, sale.name, sale.amount, sale.date, "cash"))
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                    var balance = customerDatabase.getCustomerCurrentBalance(sale.name)
                    balance += total
                    customerDatabase.setCustomerCurrentBalance(balance, sale.name)
                }

                BackupRestore.backup(context, "sale")
                BackupRestore.backup(context, "sale_details")
                BackupRestore.backup(context, "customer")
                BackupRestore.backup(context, "transaction")
                view?.findNavController()?.navigate(
                    SaleNewFragmentDirections.actionSaleNewFragmentToSaleDetailFragment(id)
                )

            } else
                Toast.makeText(context, "Customer not present ", Toast.LENGTH_SHORT).show()
        }

        binding.saleNewDate.setOnClickListener {

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this.requireContext(),
                { _, yearPick, monthOfYear, dayOfMonth ->
                    val text = dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + yearPick
                    binding.saleNewDate.setText(text)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        return binding.root
    }
}