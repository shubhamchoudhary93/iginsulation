package com.shubham.iginsulation.shopStock

import android.os.Bundle
import android.text.format.DateFormat.format
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
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

        stockList = shopStockDatabase.getAllStock()
        val adapterStock: ArrayAdapter<String> = ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_list_item_1,
            stockList
        )
        binding.shopStockName.threshold = 1
        binding.shopStockName.setAdapter(adapterStock)

        fetchAdaptor()
        setListeners()
        binding.notifications.text = "Ready"
        return binding.root
    }

    private fun setListeners() {
        binding.shopStockName.setOnItemClickListener { parent, _, position, _ ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            val stockDetailReturn = shopStockDatabase.get(selectedItem)
            binding.shopStockQuantity.setText(stockDetailReturn?.defaultReduce.toString())
            binding.shopStockQuantityShow.text = stockDetailReturn?.quantity.toString()
        }

        binding.shopStockAdd.setOnClickListener {
            val name = binding.shopStockName.text.toString()
            val stock = shopStockDatabase.get(name)

            val quantity = binding.shopStockQuantity.text.toString()
            if (stock != null) {
                if (quantity.toIntOrNull() == null) {
                    binding.notifications.text = "quantity should be numeric"
                } else {
                    stock.quantity += quantity.toInt()
                    shopStockDatabase.update(stock)
                    binding.shopStockQuantityShow.text = stock.quantity.toString()
                    val date = SimpleDateFormat("d/M/yyyy", Locale.ENGLISH).format(Date())
                    shopStockTransactionDatabase.insert(
                        ShopStockTransaction(
                            0L, true, name, if (quantity == "")
                                0
                            else
                                quantity.toInt(), date, ""
                        )
                    )
                    binding.notifications.text = name + " updated to " + stock.quantity
                }
            }

            binding.shopStockName.setText("")
            binding.shopStockQuantity.setText("")
            binding.shopStockQuantityShow.text = ""
            fetchAdaptor()
        }

        binding.shopStockMinus.setOnClickListener {
            val name = binding.shopStockName.text.toString()
            val stock = shopStockDatabase.get(name)

            val quantity = binding.shopStockQuantity.text.toString()
            if (stock != null) {
                if (quantity.toIntOrNull() == null) {
                    binding.notifications.text = "quantity should be numeric"
                } else {
                    stock.quantity -= quantity.toInt()
                    shopStockDatabase.update(stock)
                    binding.shopStockQuantityShow.text = stock.quantity.toString()
                    val date = SimpleDateFormat("d/M/yyyy", Locale.ENGLISH).format(Date())
                    shopStockTransactionDatabase.insert(
                        ShopStockTransaction(
                            0L, false, name, if (quantity == "")
                                0
                            else
                                quantity.toInt(), date, ""
                        )
                    )
                    binding.notifications.text = name + " updated to " + stock.quantity
                }
            }

            binding.shopStockName.setText("")
            binding.shopStockQuantity.setText("")
            binding.shopStockQuantityShow.text = ""
            fetchAdaptor()
        }

        binding.buttonNewShopStock.setOnClickListener {
            view?.findNavController()
                ?.navigate(ShopStockFragmentDirections.actionShopStockFragmentToShopStockNewFragment())
        }
        binding.buttonViewShopStock.setOnClickListener {
            view?.findNavController()
                ?.navigate(ShopStockFragmentDirections.actionShopStockFragmentToShopStockListFragment())
        }
        binding.buttonViewShopStockTransaction.setOnClickListener {
            view?.findNavController()
                ?.navigate(ShopStockFragmentDirections.actionShopStockFragmentToShopStockTransactionListFragment())
        }

        binding.buttonUpdateShopStock.setOnClickListener {
            view?.findNavController()
                ?.navigate(ShopStockFragmentDirections.actionShopStockFragmentToShopStockUpdateFragment())
        }
    }

    private fun fetchAdaptor() {
        val d = Date()
        val str: CharSequence = format("d/M/yyyy", d.time)
        val list = shopStockTransactionDatabase.getByDate(str as String)

        val adapter = ShopStockAdaptor(ShopStockAdaptor.ShopStockTransactionListener {
            Toast.makeText(context, "selected - $id", Toast.LENGTH_SHORT).show()
        })

        binding.list.adapter = adapter

        adapter.submitList(list)
    }
}