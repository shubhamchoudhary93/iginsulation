package com.shubham.iginsulation.stock

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
import com.shubham.iginsulation.database.stock.Stock
import com.shubham.iginsulation.database.stock.StockDatabase
import com.shubham.iginsulation.database.stock.StockDatabaseDao
import com.shubham.iginsulation.databinding.FragmentStockModifyBinding

class StockModifyFragment : Fragment() {

    private lateinit var binding: FragmentStockModifyBinding
    private lateinit var stockDatabase: StockDatabaseDao
    private lateinit var categoryList: List<String>
    private lateinit var subCategoryList: List<String>

    private var id = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_stock_modify, container, false
        )

        stockDatabase = StockDatabase.getInstance(requireContext()).stockDatabaseDao

        val args = StockModifyFragmentArgs.fromBundle(requireArguments())
        id = args.id

        fetchStock(id)
        
        categoryList = stockDatabase.getAllCategory()
        val adapterCategory: ArrayAdapter<String> = ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_list_item_1,
            categoryList
        )
        binding.stockModifyCategory.threshold = 1
        binding.stockModifyCategory.setAdapter(adapterCategory)

        subCategoryList = stockDatabase.getAllSubCategory()
        val adapterSubCategory: ArrayAdapter<String> = ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_list_item_1,
            subCategoryList
        )
        binding.stockModifySubCategory.threshold = 1
        binding.stockModifySubCategory.setAdapter(adapterSubCategory)

        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.stockModify.setOnClickListener {

            val name = binding.stockModifyName.text.toString()
            val category = binding.stockModifyCategory.text.toString()
            val subCategory = binding.stockModifySubCategory.text.toString()
            var rate = binding.stockModifyRate.text.toString()
            if (rate.toFloatOrNull() == null){
                Toast.makeText(context, "rate should be numeric", Toast.LENGTH_SHORT).show()
                rate = ""
            }
            var percentage = binding.stockModifyPercentage.text.toString()
            if (percentage.toFloatOrNull() == null){
                Toast.makeText(context, "percentage should be numeric", Toast.LENGTH_SHORT).show()
                percentage = ""
            }

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
                    if (rate == "")
                        0F
                    else
                        rate.toFloat(),
                    if (percentage == "")
                        0F
                    else
                        percentage.toFloat()
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
        rate: Float,
        percentage: Float
    ) {
        val stock = Stock()
        stock.id = id
        stock.name = name
        stock.category = category
        stock.subCategory = subCategory
        stock.rate = rate
        stock.percentage = percentage

        try {
            stockDatabase.update(stock)
        } catch (e: Exception) {
            e.printStackTrace()
        }
                view?.findNavController()?.navigate(
                    StockModifyFragmentDirections.actionStockModifyFragmentToStockDetailFragment(
                        id
                    )
                )
    }

    private fun fetchStock(id: Long) {
        val stock = if (id == 0L) {
            try {
                stockDatabase.getLastStock()
            } catch (e: Exception) {
                e.printStackTrace()
                Stock()
            }
        } else {
            try {
                stockDatabase.get(id)!!
            } catch (e: Exception) {
                e.printStackTrace()
                Stock()
            }
        }
        setStockData(stock)
    }

    private fun setStockData(stock: Stock) {
        binding.stockModifyName.text = stock.name
        binding.stockModifyCategory.setText(stock.category)
        binding.stockModifySubCategory.setText(stock.subCategory)
        binding.stockModifyRate.setText(stock.rate.toString())
        binding.stockModifyPercentage.setText(stock.percentage.toString())
    }
}