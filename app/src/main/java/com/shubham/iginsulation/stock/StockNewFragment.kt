package com.shubham.iginsulation.stock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.R
import com.shubham.iginsulation.database.stock.Stock
import com.shubham.iginsulation.database.stock.StockDatabase
import com.shubham.iginsulation.database.stock.StockDatabaseDao
import com.shubham.iginsulation.databinding.FragmentStockNewBinding

class StockNewFragment : Fragment() {

    private lateinit var binding: FragmentStockNewBinding
    private lateinit var stockDatabase: StockDatabaseDao
    private lateinit var categoryList: List<String>
    private lateinit var subCategoryList: List<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_stock_new, container, false
        )

        stockDatabase = StockDatabase.getInstance(requireContext()).stockDatabaseDao

        categoryList = stockDatabase.getAllCategory()
        val adapterCategory: ArrayAdapter<String> = ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_list_item_1,
            categoryList
        )
        binding.stockNewCategory.threshold = 1
        binding.stockNewCategory.setAdapter(adapterCategory)

        subCategoryList = stockDatabase.getAllSubCategory()
        val adapterSubCategory: ArrayAdapter<String> = ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_list_item_1,
            subCategoryList
        )
        binding.stockNewSubCategory.threshold = 1
        binding.stockNewSubCategory.setAdapter(adapterSubCategory)

        setListeners()
        return binding.root
    }

    private fun setListeners() {
        binding.stockNewAdd.setOnClickListener {

            val name = binding.stockNewName.text.toString()
            val category = binding.stockNewCategory.text.toString()
            val subCategory = binding.stockNewSubCategory.text.toString()
            var rate = binding.stockNewRate.text.toString()
            if (rate.toFloatOrNull() == null){
                Toast.makeText(context, "rate should be numeric", Toast.LENGTH_SHORT).show()
                rate = ""
            }
            var percentage = binding.stockNewPercentage.text.toString()
            if (percentage.toFloatOrNull() == null){
                Toast.makeText(context, "percentage should be numeric", Toast.LENGTH_SHORT).show()
                percentage = ""
            }

            if (name != "") {
                insertStock(
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

    private fun insertStock(
        name: String,
        category: String,
        subCategory: String,
        rate: Float,
        percentage: Float
    ) {
        val stock = Stock()
        stock.name = name
        stock.category = category
        stock.subCategory = subCategory
        stock.rate = rate
        stock.percentage = percentage

        try {
            stockDatabase.insert(stock)
        } catch (e: Exception) {
            e.printStackTrace()
        }
                view?.findNavController()?.navigate(
                    StockNewFragmentDirections.actionStockNewFragmentToStockDetailFragment(
                        0L
                    )
                )
    }
}