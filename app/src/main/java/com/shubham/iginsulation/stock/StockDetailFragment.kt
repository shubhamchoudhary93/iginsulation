package com.shubham.iginsulation.stock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.R
import com.shubham.iginsulation.customer.CustomerDetailFragmentDirections
import com.shubham.iginsulation.database.stock.Stock
import com.shubham.iginsulation.database.stock.StockDatabase
import com.shubham.iginsulation.database.stock.StockDatabaseDao
import com.shubham.iginsulation.databinding.FragmentStockDetailBinding

class StockDetailFragment : Fragment() {

    private lateinit var binding: FragmentStockDetailBinding
    private lateinit var stockDatabase: StockDatabaseDao

    private var id = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_stock_detail, container, false
        )

        stockDatabase = StockDatabase.getInstance(requireContext()).stockDatabaseDao

        val args = StockDetailFragmentArgs.fromBundle(requireArguments())
        id = args.id

        fetchStock(id)

        binding.stockDetailDelete.setOnClickListener {
            stockDatabase.delete(id)
            view?.findNavController()?.navigate(
                StockDetailFragmentDirections.actionStockDetailFragmentToStockListFragment()
            )
        }

        binding.stockDetailModify.setOnClickListener {
            view?.findNavController()?.navigate(
                StockDetailFragmentDirections.actionStockDetailFragmentToStockModifyFragment(id)
            )
        }

        return binding.root
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
        binding.stockDetailName.text = stock.name
        binding.stockDetailCategory.text = stock.category
        binding.stockDetailSubCategory.text = stock.subCategory
        binding.stockDetailRate.text = stock.rate.toString()
        binding.stockDetailPercentage.text = stock.percentage.toString()
    }
}