package com.shubham.iginsulation.shopStock

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
import com.shubham.iginsulation.database.shopStockTransaction.ShopStockTransaction
import com.shubham.iginsulation.database.shopStockTransaction.ShopStockTransactionDatabase
import com.shubham.iginsulation.database.shopStockTransaction.ShopStockTransactionDatabaseDao
import com.shubham.iginsulation.database.shopstock.ShopStockDatabase
import com.shubham.iginsulation.database.shopstock.ShopStockDatabaseDao
import com.shubham.iginsulation.databinding.FragmentShopStockTransactionModifyBinding
import java.util.*
import kotlin.math.roundToInt

class ShopStockTransactionModifyFragment : Fragment() {

    private lateinit var binding: FragmentShopStockTransactionModifyBinding
    private lateinit var shopStockTransactionDatabase: ShopStockTransactionDatabaseDao
    private lateinit var shopStockDatabase: ShopStockDatabaseDao
    private lateinit var shopStocks: List<String>

    private var id = 0L
    private var shopStockTransaction = ShopStockTransaction()
    private var oldAdd = false
    private var oldQuantity = 0F
    private var oldName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shop_stock_transaction_modify, container, false
        )

        shopStockTransactionDatabase =
            ShopStockTransactionDatabase.getInstance(requireContext()).shopStockTransactionDatabaseDao
        shopStockDatabase = ShopStockDatabase.getInstance(requireContext()).shopStockDatabaseDao

        shopStocks = shopStockDatabase.getShopStockNames()
        val adapterCategory: ArrayAdapter<String> = ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_list_item_1,
            shopStocks
        )
        binding.name.threshold = 1
        binding.name.setAdapter(adapterCategory)

        val args = ShopStockTransactionModifyFragmentArgs.fromBundle(requireArguments())
        id = args.id

        fetchShopStockTransaction(id)

        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.modify.setOnClickListener {

            val name = binding.name.text.toString()
            var quantity = binding.quantity.text.toString()
            if (quantity.toFloatOrNull() == null) {
                Toast.makeText(context, "quantity should be numeric", Toast.LENGTH_SHORT).show()
                quantity = ""
            }
            val date = binding.date.text.toString()
            val detail = binding.detail.text.toString()

            if (name != "" || shopStocks.contains(name)) {
                modifyShopStockTransaction(
                    binding.add.isChecked,
                    name,
                    if (quantity == "")
                        0
                    else
                        quantity.toFloat().roundToInt(),
                    date,
                    detail
                )
                if (oldName == name) {
                    val oldQuantity1 = shopStockDatabase.getShopStockQuantity(name)
                    val newQuantity = if (oldAdd && binding.add.isChecked) {
                        oldQuantity1 - oldQuantity + quantity.toFloat()
                    } else if (oldAdd && !(binding.add.isChecked)) {
                        oldQuantity1 - oldQuantity - quantity.toFloat()
                    } else if (!oldAdd && binding.add.isChecked) {
                        oldQuantity1 + oldQuantity + quantity.toFloat()
                    } else if (!oldAdd && !(binding.add.isChecked)) {
                        oldQuantity1 + oldQuantity - quantity.toFloat()
                    } else 0F
                    shopStockDatabase.setShopStockQuantity(newQuantity, name)
                } else {
                    val oldQuantityOldName = shopStockDatabase.getShopStockQuantity(oldName)
                    val newQuantityOldName = if (oldAdd) {
                        oldQuantityOldName - oldQuantity
                    } else {
                        oldQuantityOldName + oldQuantity
                    }
                    shopStockDatabase.setShopStockQuantity(newQuantityOldName, oldName)

                    val oldQuantityNewName = shopStockDatabase.getShopStockQuantity(name)
                    val newQuantityNewName = if (binding.add.isChecked) {
                        oldQuantityNewName + quantity.toFloat()
                    } else {
                        oldQuantityNewName - quantity.toFloat()
                    }
                    shopStockDatabase.setShopStockQuantity(newQuantityNewName, name)
                }
            } else
                Toast.makeText(context, "shopStock doesn't exist", Toast.LENGTH_SHORT).show()
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

    private fun modifyShopStockTransaction(
        add: Boolean,
        name: String,
        quantity: Int,
        date: String,
        detail: String
    ) {
        try {
            shopStockTransactionDatabase.update(
                ShopStockTransaction(
                    id,
                    add,
                    name,
                    quantity,
                    date,
                    detail
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        view?.findNavController()
            ?.navigate(
                ShopStockTransactionModifyFragmentDirections.actionShopStockTransactionModifyFragmentToShopStockTransactionDetailFragment(
                    id
                )
            )
    }

    private fun fetchShopStockTransaction(id: Long) {
        shopStockTransaction = if (id == 0L) {
            try {
                shopStockTransactionDatabase.getLastShopStockTransaction()
            } catch (e: Exception) {
                e.printStackTrace()
                ShopStockTransaction()
            }
        } else {
            try {
                shopStockTransactionDatabase.get(id)!!
            } catch (e: Exception) {
                e.printStackTrace()
                ShopStockTransaction()
            }
        }
        setShopStockTransactionData(shopStockTransaction)
    }

    private fun setShopStockTransactionData(shopStockTransaction: ShopStockTransaction) {
        binding.add.isChecked = shopStockTransaction.add
        binding.name.setText(shopStockTransaction.stock)
        binding.quantity.setText(shopStockTransaction.quantity.toString())
        binding.date.setText(shopStockTransaction.date)
        binding.detail.setText(shopStockTransaction.detail)

        oldAdd = shopStockTransaction.add
        oldQuantity = shopStockTransaction.quantity.toFloat()
        oldName = shopStockTransaction.stock
    }
}