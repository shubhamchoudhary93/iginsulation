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
import com.shubham.iginsulation.R
import com.shubham.iginsulation.database.shopstock.ShopStock
import com.shubham.iginsulation.database.shopstock.ShopStockDatabase
import com.shubham.iginsulation.database.shopstock.ShopStockDatabaseDao
import com.shubham.iginsulation.databinding.FragmentShopStockModifyBinding

class ShopStockModifyFragment : Fragment() {

    private lateinit var binding: FragmentShopStockModifyBinding
    private lateinit var shopStockDatabase: ShopStockDatabaseDao
    private lateinit var categoryList: List<String>
    private lateinit var subCategoryList: List<String>

    private var id = 0L

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
        binding.shopStockModifyCategory.threshold = 1
        binding.shopStockModifyCategory.setAdapter(adapterCategory)

        subCategoryList = shopStockDatabase.getAllSubCategory()
        val adapterSubCategory: ArrayAdapter<String> = ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_list_item_1,
            subCategoryList
        )
        binding.shopStockModifySubCategory.threshold = 1
        binding.shopStockModifySubCategory.setAdapter(adapterSubCategory)

        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.shopStockModifyModify.setOnClickListener {

            val name = binding.shopStockModifyName.text.toString()
            val category = binding.shopStockModifyCategory.text.toString()
            val subCategory = binding.shopStockModifySubCategory.text.toString()
            var rate = binding.shopStockModifyRate.text.toString()
            if (rate.toFloatOrNull() == null) {
                Toast.makeText(context, "rate should be numeric", Toast.LENGTH_SHORT).show()
                rate = ""
            }
            var quantity = binding.shopStockModifyQuantity.text.toString()
            if (quantity.toIntOrNull() == null) {
                Toast.makeText(context, "percentage should be numeric", Toast.LENGTH_SHORT).show()
                quantity = ""
            }

            var minQuantity = binding.shopStockModifyMinQuantity.text.toString()
            if (minQuantity.toIntOrNull() == null) {
                Toast.makeText(context, "minQuantity should be numeric", Toast.LENGTH_SHORT).show()
                minQuantity = ""
            }

            var defaultReduce = binding.shopStockModifyDefaultReduce.text.toString()
            if (defaultReduce.toIntOrNull() == null) {
                Toast.makeText(context, "defaultReduce should be numeric", Toast.LENGTH_SHORT).show()
                defaultReduce = ""
            }

            val seller = binding.shopStockModifySeller.text.toString()

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

        try {
            shopStockDatabase.update(shopStock)
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
        binding.shopStockModifyName.setText(shopStock.name)
        binding.shopStockModifyCategory.setText(shopStock.category)
        binding.shopStockModifySubCategory.setText(shopStock.subCategory)
        binding.shopStockModifyQuantity.setText(shopStock.quantity.toString())
        binding.shopStockModifyMinQuantity.setText(shopStock.minQuantity.toString())
        binding.shopStockModifyDefaultReduce.setText(shopStock.defaultReduce.toString())
        binding.shopStockModifyRate.setText(shopStock.rate.toString())
        binding.shopStockModifySeller.setText(shopStock.seller)
    }
}