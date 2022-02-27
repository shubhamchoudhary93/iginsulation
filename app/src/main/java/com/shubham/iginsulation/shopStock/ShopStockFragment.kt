package com.shubham.iginsulation.shopStock

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.format.DateFormat.format
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.BackupRestore
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
//    private lateinit var data: SharedPreferences

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
        binding.name.threshold = 1
        binding.name.setAdapter(adapterStock)

        fetchAdaptor()
        setListeners()

        binding.shopStockQuantity.post {
            val params: ViewGroup.LayoutParams = binding.name.layoutParams
            params.height = binding.shopStockQuantity.height
            binding.name.layoutParams = params
        }

        binding.notifications.text = "Ready"
        return binding.root
    }

    private fun setListeners() {
        binding.name.setOnItemClickListener { parent, _, position, _ ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            val stockDetailReturn = shopStockDatabase.get(selectedItem)
            binding.shopStockQuantity.setText(stockDetailReturn?.defaultReduce.toString())
            binding.shopStockQuantityShow.text = stockDetailReturn?.quantity.toString()
        }

        binding.shopStockAdd.setOnClickListener {
            val name = binding.name.text.toString()
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
//                    data = requireActivity().getSharedPreferences("IGInsulation", Context.MODE_PRIVATE)
//                    if(data.getString("DataUpdateRequired", "") != "1"){
//                        val prefsEditor: SharedPreferences.Editor = data.edit()
//                        prefsEditor.putString("DataUpdateRequired", "1")
//                        prefsEditor.apply()
//                    }

                    binding.notifications.text = name + " updated to " + stock.quantity
                }
            }

            binding.name.setText("")
            binding.shopStockQuantity.setText("")
            binding.shopStockQuantityShow.text = ""
            fetchAdaptor()
        }

        binding.shopStockMinus.setOnClickListener {
            val name = binding.name.text.toString()
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

//                    data = requireActivity().getSharedPreferences("IGInsulation", Context.MODE_PRIVATE)
//                    if(data.getString("DataUpdateRequired", "") != "1"){
//                        val prefsEditor: SharedPreferences.Editor = data.edit()
//                        prefsEditor.putString("DataUpdateRequired", "1")
//                        prefsEditor.apply()
//                    }

                    binding.notifications.text = name + " updated to " + stock.quantity
                }
            }

            binding.name.setText("")
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

    }

    override fun onResume() {
        super.onResume()
    }
}