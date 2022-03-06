package com.shubham.iginsulation.shopStock

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
import com.shubham.iginsulation.database.shopstock.ShopStock
import com.shubham.iginsulation.database.shopstock.ShopStockDatabase
import com.shubham.iginsulation.database.shopstock.ShopStockDatabaseDao
import com.shubham.iginsulation.databinding.FragmentShopStockNewBinding
import java.text.SimpleDateFormat
import java.util.*

class ShopStockNewFragment : Fragment() {

    private lateinit var binding: FragmentShopStockNewBinding
    private lateinit var shopStockDatabase: ShopStockDatabaseDao
    private lateinit var categoryList: List<String>
    private lateinit var subCategoryList: List<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shop_stock_new, container, false
        )

        shopStockDatabase = ShopStockDatabase.getInstance(requireContext()).shopStockDatabaseDao

        categoryList = shopStockDatabase.getAllCategory()
        val adapterCategory: ArrayAdapter<String> = ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_list_item_1,
            categoryList
        )
        binding.categoryAuto.threshold = 1
        binding.categoryAuto.setAdapter(adapterCategory)

        subCategoryList = shopStockDatabase.getAllSubCategory()
        val adapterSubCategory: ArrayAdapter<String> = ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_list_item_1,
            subCategoryList
        )

        binding.subCategoryAuto.threshold = 1
        binding.subCategoryAuto.setAdapter(adapterSubCategory)

        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.add.setOnClickListener {

            val name = binding.name.text.toString()
            val category = binding.categoryAuto.text.toString()
            val subCategory = binding.subCategoryAuto.text.toString()
            var rate = binding.rate.text.toString()
            if (rate.toFloatOrNull() == null) {
                Toast.makeText(context, "rate should be numeric", Toast.LENGTH_SHORT).show()
                rate = ""
            }
            var quantity = binding.quantity.text.toString()
            if (quantity.toIntOrNull() == null) {
                Toast.makeText(context, "percentage should be numeric", Toast.LENGTH_SHORT).show()
                quantity = ""
            }

            var minQuantity = binding.minQuantity.text.toString()
            if (minQuantity.toIntOrNull() == null) {
                Toast.makeText(context, "minQuantity should be numeric", Toast.LENGTH_SHORT).show()
                minQuantity = ""
            }

            var defaultReduce = binding.defaultReduce.text.toString()
            if (defaultReduce.toIntOrNull() == null) {
                Toast.makeText(context, "defaultReduce should be numeric", Toast.LENGTH_SHORT)
                    .show()
                defaultReduce = ""
            }

            val seller = binding.seller.text.toString()

            if (name != "") {
                insertShopStock(
                    name,
                    category,
                    subCategory,
                    if (quantity == "")
                        0
                    else
                        quantity.toInt(),
                    if (minQuantity == "")
                        0
                    else
                        minQuantity.toInt(),
                    if (defaultReduce == "")
                        0
                    else
                        defaultReduce.toInt(),
                    if (rate == "")
                        0F
                    else
                        rate.toFloat(),
                    seller
                )
            } else {
                Toast.makeText(this.context, "Name is empty", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun insertShopStock(
        name: String,
        category: String,
        subCategory: String,
        quantity: Int,
        minQuantity: Int,
        defaultReduce: Int,
        rate: Float,
        seller: String
    ) {
        val shopStock = ShopStock()
        shopStock.name = name
        shopStock.category = category
        shopStock.subCategory = subCategory
        shopStock.quantity = quantity
        shopStock.minQuantity = minQuantity
        shopStock.defaultReduce = defaultReduce
        shopStock.rate = rate
        shopStock.seller = seller
        shopStock.opening = quantity
        shopStock.date = SimpleDateFormat("d/M/yyyy", Locale.ENGLISH).format(Date())

        try {
            shopStockDatabase.insert(shopStock)
            BackupRestore.backup(context, "shop_stock")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        view?.findNavController()?.navigate(
            ShopStockNewFragmentDirections.actionShopStockNewFragmentToShopStockDetailFragment(
                0L
            )
        )
    }
}