package com.shubham.iginsulation.shopStock

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.format.DateFormat.format
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.BackupRestore.backup
import com.shubham.iginsulation.R
import com.shubham.iginsulation.database.shopStockTransaction.ShopStockTransaction
import com.shubham.iginsulation.database.shopStockTransaction.ShopStockTransactionDatabase
import com.shubham.iginsulation.database.shopStockTransaction.ShopStockTransactionDatabaseDao
import com.shubham.iginsulation.database.shopstock.ShopStockDatabase
import com.shubham.iginsulation.database.shopstock.ShopStockDatabaseDao
import com.shubham.iginsulation.databinding.FragmentShopStockBinding
import java.text.SimpleDateFormat
import java.util.*

class ShopStockFragment : Fragment() {

    private lateinit var binding: FragmentShopStockBinding
    private lateinit var shopStockDatabase: ShopStockDatabaseDao
    private lateinit var shopStockTransactionDatabase: ShopStockTransactionDatabaseDao
    private lateinit var stockList: List<String>
    private var notification = "Ready"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shop_stock, container, false
        )

        shopStockDatabase = ShopStockDatabase.getInstance(requireContext()).shopStockDatabaseDao
        shopStockTransactionDatabase =
            ShopStockTransactionDatabase.getInstance(requireContext()).shopStockTransactionDatabaseDao

        //Retrieving Stock Name List from database to put into AutoComplete Edittext
        stockList = shopStockDatabase.getAllStock()
        val adapterStock: ArrayAdapter<String> = ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_list_item_1,
            stockList
        )
        binding.name.threshold = 1
        binding.name.setAdapter(adapterStock)

        fetchAdaptor()
        setListeners()

        //resizing autocomplete edittext to normal height
        //as it tends to shrink when nothing is selected at page load
        binding.quantity.post {
            val params: ViewGroup.LayoutParams = binding.name.layoutParams
            params.height = binding.quantity.height
            binding.name.layoutParams = params
        }

        binding.date.setText(SimpleDateFormat("d/M/yyyy", Locale.ENGLISH).format(Date()))
        binding.notifications.text = notification
        return binding.root
    }

    private fun setListeners() {

        //binding the backup icon to backup code
        binding.header.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.backup -> {
                    backup(context, "shop_stock")
                    backup(context, "shop_stock_transaction")
                    true
                }
                else -> false
            }
        }

        //fetching current quantity and default reduce of the stock selected from autocomplete
        binding.name.setOnItemClickListener { parent, _, position, _ ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            val stockDetailReturn = shopStockDatabase.get(selectedItem)
            binding.quantity.setText(stockDetailReturn?.defaultReduce.toString())
            binding.quantityShow.text = stockDetailReturn?.quantity.toString()
        }

        binding.add.setOnClickListener {
            val name = binding.name.text.toString()
            val stock = shopStockDatabase.get(name)

            val quantity = binding.quantity.text.toString()
            if (stock != null) {
                if (quantity.toIntOrNull() == null) {
                    "quantity should be numeric".also { binding.notifications.text = it }
                } else {
                    stock.quantity += quantity.toInt()
                    shopStockDatabase.update(stock)
                    binding.quantityShow.text = stock.quantity.toString()
                    val date = binding.date.text.toString()
                    shopStockTransactionDatabase.insert(
                        ShopStockTransaction(
                            0L, true, name, if (quantity == "")
                                0
                            else
                                quantity.toInt(), date, ""
                        )
                    )

                    notification = name + " updated to " + stock.quantity
                    binding.notifications.text = notification
                }
            }

            binding.name.setText("")
            binding.quantity.setText("")
            binding.quantityShow.text = ""
            fetchAdaptor()
        }

        binding.minus.setOnClickListener {
            val name = binding.name.text.toString()
            val stock = shopStockDatabase.get(name)

            val quantity = binding.quantity.text.toString()
            if (stock != null) {
                if (quantity.toIntOrNull() == null) {
                    notification = "quantity should be numeric"
                    binding.notifications.text = notification
                } else {
                    stock.quantity -= quantity.toInt()
                    shopStockDatabase.update(stock)
                    binding.quantityShow.text = stock.quantity.toString()
                    val date = binding.date.text.toString()
                    shopStockTransactionDatabase.insert(
                        ShopStockTransaction(
                            0L, false, name, if (quantity == "")
                                0
                            else
                                quantity.toInt(), date, ""
                        )
                    )

                    notification = name + " updated to " + stock.quantity
                    binding.notifications.text = notification
                }
            }

            binding.name.setText("")
            binding.quantity.setText("")
            binding.quantityShow.text = ""
            fetchAdaptor()
        }

        binding.buttonNew.setOnClickListener {
            view?.findNavController()
                ?.navigate(ShopStockFragmentDirections.actionShopStockFragmentToShopStockNewFragment())
        }
        binding.buttonView.setOnClickListener {
            view?.findNavController()
                ?.navigate(ShopStockFragmentDirections.actionShopStockFragmentToShopStockListFragment())
        }
        binding.buttonViewTransaction.setOnClickListener {
            view?.findNavController()
                ?.navigate(ShopStockFragmentDirections.actionShopStockFragmentToShopStockTransactionListFragment())
        }

        binding.buttonUpdateShopStock.setOnClickListener {
            view?.findNavController()
                ?.navigate(ShopStockFragmentDirections.actionShopStockFragmentToShopStockUpdateFragment())
        }

        binding.date.setOnClickListener {

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this.requireContext(),
                { _, yearPick, monthOfYear, dayOfMonth ->
                    val text = dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + yearPick
                    binding.date.setText(text)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
    }

    private fun fetchAdaptor() {
        val d = Date()
        val str: CharSequence = format("d/M/yyyy", d.time)
        val list = shopStockTransactionDatabase.getByDate(str as String)

        val adapter = ShopStockAdaptor(ShopStockAdaptor.ShopStockTransactionListener {
            view?.findNavController()?.navigate(
                ShopStockFragmentDirections.actionShopStockFragmentToShopStockTransactionDetailFragment(
                    it
                )
            )
        })

        binding.list.adapter = adapter
        adapter.submitList(list)
    }

    override fun onPause() {
        super.onPause()
        backup(context, "shop_stock_transaction")
        backup(context, "shop_stock")
    }
}