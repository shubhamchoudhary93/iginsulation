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
import com.shubham.iginsulation.databinding.FragmentShopStockModifyBinding
import java.text.SimpleDateFormat
import java.util.*

class ShopStockModifyFragment : Fragment() {

    private lateinit var binding: FragmentShopStockModifyBinding
    private lateinit var shopStockDatabase: ShopStockDatabaseDao
    private lateinit var categoryList: List<String>
    private lateinit var subCategoryList: List<String>

    private var id = 0L
    private var previousQuantity = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shop_stock_modify, container, false
        )

        shopStockDatabase = ShopStockDatabase.getInstance(requireContext()).shopStockDatabaseDao

        val args = ShopStockModifyFragmentArgs.fromBundle(requireArguments())
        id = args.id

        fetchStock(id)

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

        binding.updateDate.setOnClickListener {
            shopStockDatabase.updateDate(id, SimpleDateFormat("d/M/yyyy", Locale.ENGLISH).format(Date()))
        }
        binding.modify.setOnClickListener {

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
                modifyStock(
                    name,
                    if (category == "")
                        "category"
                    else
                        category,
                    if (subCategory == "")
                        "subCategory"
                    else
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

    private fun modifyStock(
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
        shopStock.id = id
        shopStock.name = name
        shopStock.category = category
        shopStock.subCategory = subCategory
        shopStock.quantity = quantity
        shopStock.minQuantity = minQuantity
        shopStock.defaultReduce = defaultReduce
        shopStock.rate = rate
        shopStock.seller = seller

        if(previousQuantity != quantity){
            shopStock.opening = quantity
            shopStock.date = SimpleDateFormat("d/M/yyyy", Locale.ENGLISH).format(Date())
        }
        try {
            shopStockDatabase.update(shopStock)
            BackupRestore.backup(context, "shop_stock")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        view?.findNavController()?.navigate(
            ShopStockModifyFragmentDirections.actionShopStockModifyFragmentToShopStockDetailFragment(
                id
            )
        )
    }

    private fun fetchStock(id: Long) {
        val shopStock = if (id == 0L) {
            try {
                shopStockDatabase.getLastShopStock()
            } catch (e: Exception) {
                e.printStackTrace()
                ShopStock()
            }
        } else {
            try {
                shopStockDatabase.get(id)!!
            } catch (e: Exception) {
                e.printStackTrace()
                ShopStock()
            }
        }
        setStockData(shopStock)
    }

    private fun setStockData(shopStock: ShopStock) {
        binding.name.setText(shopStock.name)
        binding.categoryAuto.setText(shopStock.category)
        binding.subCategoryAuto.setText(shopStock.subCategory)
        binding.quantity.setText(shopStock.quantity.toString())
        previousQuantity = shopStock.quantity
        binding.minQuantity.setText(shopStock.minQuantity.toString())
        binding.defaultReduce.setText(shopStock.defaultReduce.toString())
        binding.rate.setText(shopStock.rate.toString())
        binding.seller.setText(shopStock.seller)
    }
}