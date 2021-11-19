package com.shubham.iginsulation.stock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.R
import com.shubham.iginsulation.database.stock.Stock
import com.shubham.iginsulation.database.stock.StockDatabase
import com.shubham.iginsulation.database.stock.StockDatabaseDao
import com.shubham.iginsulation.databinding.FragmentStockListBinding

class StockListFragment : Fragment() {

    private lateinit var binding: FragmentStockListBinding
    private lateinit var stockDatabase: StockDatabaseDao
    private var currentPage = 1
    private var totalPage = 1
    private lateinit var stockList: List<Stock>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_stock_list, container, false
        )

        stockDatabase = StockDatabase.getInstance(requireContext()).stockDatabaseDao

        binding.stockOne.visibility = View.GONE
        binding.stockTwo.visibility = View.GONE
        binding.stockThree.visibility = View.GONE
        binding.stockFour.visibility = View.GONE
        binding.stockFive.visibility = View.GONE
        binding.stockSix.visibility = View.GONE
        binding.stockSeven.visibility = View.GONE
        binding.stockEight.visibility = View.GONE
        binding.stockNine.visibility = View.GONE
        binding.stockTen.visibility = View.GONE
        binding.buttonNextPage.visibility = View.GONE
        binding.buttonPreviousPage.visibility = View.GONE

        fetchStockList()

        return binding.root
    }

    private fun fetchStockList() {
        try {
            stockList = stockDatabase.getList()
            setStockData()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setStockData() {
        //calculating number of pages
        totalPage = stockList.size / 10
        if (stockList.size % 10 > 0)
            totalPage += 1

        binding.page.text = currentPage.toString()
        binding.totalPage.text = totalPage.toString()

        calculatePageData()

        binding.buttonNextPage.setOnClickListener {
            currentPage += 1
            calculatePageData()
        }

        binding.buttonPreviousPage.setOnClickListener {
            currentPage -= 1
            calculatePageData()
        }
    }

    private fun calculatePageData() {
        binding.page.text = currentPage.toString()
        if (totalPage > currentPage)
            binding.buttonNextPage.visibility = View.VISIBLE
        else
            binding.buttonNextPage.visibility = View.GONE
        if (currentPage > 1)
            binding.buttonPreviousPage.visibility = View.VISIBLE
        else
            binding.buttonPreviousPage.visibility = View.GONE
        val startingIndex = (currentPage - 1) * 10

        val numberOfItems: Int = if (totalPage > currentPage)
            10
        else
            stockList.size % 10

        if (numberOfItems > 0) {
            binding.stockOne.visibility = View.VISIBLE
            binding.stockOneName.text = stockList[startingIndex].name
            binding.stockOneCategory.text = stockList[startingIndex].category
            binding.stockOneSubCategory.text = stockList[startingIndex].subCategory
            binding.stockOne.setOnClickListener {
                view?.findNavController()?.navigate(
                    StockListFragmentDirections.actionStockListFragmentToStockDetailFragment(
                        stockList[startingIndex].id
                    )
                )
            }
        } else
            binding.stockOne.visibility = View.GONE
        if (numberOfItems > 1) {
            binding.stockTwo.visibility = View.VISIBLE
            binding.stockTwoName.text = stockList[startingIndex + 1].name
            binding.stockTwoCategory.text = stockList[startingIndex + 1].category
            binding.stockTwoSubCategory.text = stockList[startingIndex + 1].subCategory
            binding.stockTwo.setOnClickListener {
                view?.findNavController()?.navigate(
                    StockListFragmentDirections.actionStockListFragmentToStockDetailFragment(
                        stockList[startingIndex + 1].id
                    )
                )
            }
        } else
            binding.stockTwo.visibility = View.GONE
        if (numberOfItems > 2) {
            binding.stockThree.visibility = View.VISIBLE
            binding.stockThreeName.text = stockList[startingIndex + 2].name
            binding.stockThreeCategory.text = stockList[startingIndex + 2].category
            binding.stockThreeSubCategory.text = stockList[startingIndex + 2].subCategory
            binding.stockThree.setOnClickListener {
                view?.findNavController()?.navigate(
                    StockListFragmentDirections.actionStockListFragmentToStockDetailFragment(
                        stockList[startingIndex + 2].id
                    )
                )
            }
        } else
            binding.stockThree.visibility = View.GONE
        if (numberOfItems > 3) {
            binding.stockFour.visibility = View.VISIBLE
            binding.stockFourName.text = stockList[startingIndex + 3].name
            binding.stockFourCategory.text = stockList[startingIndex + 3].category
            binding.stockFourSubCategory.text = stockList[startingIndex + 3].subCategory
            binding.stockFour.setOnClickListener {
                view?.findNavController()?.navigate(
                    StockListFragmentDirections.actionStockListFragmentToStockDetailFragment(
                        stockList[startingIndex + 3].id
                    )
                )
            }
        } else
            binding.stockFour.visibility = View.GONE
        if (numberOfItems > 4) {
            binding.stockFive.visibility = View.VISIBLE
            binding.stockFiveName.text = stockList[startingIndex + 4].name
            binding.stockFiveCategory.text = stockList[startingIndex + 4].category
            binding.stockFiveSubCategory.text = stockList[startingIndex + 4].subCategory
            binding.stockFive.setOnClickListener {
                view?.findNavController()?.navigate(
                    StockListFragmentDirections.actionStockListFragmentToStockDetailFragment(
                        stockList[startingIndex + 4].id
                    )
                )
            }
        } else
            binding.stockFive.visibility = View.GONE
        if (numberOfItems > 5) {
            binding.stockSix.visibility = View.VISIBLE
            binding.stockSixName.text = stockList[startingIndex + 5].name
            binding.stockSixCategory.text = stockList[startingIndex + 5].category
            binding.stockSixSubCategory.text = stockList[startingIndex + 5].subCategory
            binding.stockSix.setOnClickListener {
                view?.findNavController()?.navigate(
                    StockListFragmentDirections.actionStockListFragmentToStockDetailFragment(
                        stockList[startingIndex + 5].id
                    )
                )
            }
        } else
            binding.stockSix.visibility = View.GONE
        if (numberOfItems > 6) {
            binding.stockSeven.visibility = View.VISIBLE
            binding.stockSevenName.text = stockList[startingIndex + 6].name
            binding.stockSevenCategory.text = stockList[startingIndex + 6].category
            binding.stockSevenSubCategory.text = stockList[startingIndex + 6].subCategory
            binding.stockSeven.setOnClickListener {
                view?.findNavController()?.navigate(
                    StockListFragmentDirections.actionStockListFragmentToStockDetailFragment(
                        stockList[startingIndex + 6].id
                    )
                )
            }
        } else
            binding.stockSeven.visibility = View.GONE
        if (numberOfItems > 7) {
            binding.stockEight.visibility = View.VISIBLE
            binding.stockEightName.text = stockList[startingIndex + 7].name
            binding.stockEightCategory.text = stockList[startingIndex + 7].category
            binding.stockEightSubCategory.text = stockList[startingIndex + 7].subCategory
            binding.stockEight.setOnClickListener {
                view?.findNavController()?.navigate(
                    StockListFragmentDirections.actionStockListFragmentToStockDetailFragment(
                        stockList[startingIndex + 7].id
                    )
                )
            }
        } else
            binding.stockEight.visibility = View.GONE
        if (numberOfItems > 8) {
            binding.stockNine.visibility = View.VISIBLE
            binding.stockNineName.text = stockList[startingIndex + 8].name
            binding.stockNineCategory.text = stockList[startingIndex + 8].category
            binding.stockNineSubCategory.text = stockList[startingIndex + 8].subCategory
            binding.stockNine.setOnClickListener {
                view?.findNavController()?.navigate(
                    StockListFragmentDirections.actionStockListFragmentToStockDetailFragment(
                        stockList[startingIndex + 8].id
                    )
                )
            }
        } else
            binding.stockNine.visibility = View.GONE
        if (numberOfItems > 9) {
            binding.stockTen.visibility = View.VISIBLE
            binding.stockTenName.text = stockList[startingIndex + 9].name
            binding.stockTenCategory.text = stockList[startingIndex + 9].category
            binding.stockTenSubCategory.text = stockList[startingIndex + 9].subCategory
            binding.stockTen.setOnClickListener {
                view?.findNavController()?.navigate(
                    StockListFragmentDirections.actionStockListFragmentToStockDetailFragment(
                        stockList[startingIndex + 9].id
                    )
                )
            }
        } else
            binding.stockTen.visibility = View.GONE
    }
}
