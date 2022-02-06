package com.shubham.iginsulation.shopStock

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.R
import com.shubham.iginsulation.database.shopstock.ShopStock
import com.shubham.iginsulation.database.shopstock.ShopStockDatabase
import com.shubham.iginsulation.database.shopstock.ShopStockDatabaseDao
import com.shubham.iginsulation.databinding.FragmentShopStockListBinding

class ShopStockListFragment : Fragment() {

    private lateinit var binding: FragmentShopStockListBinding
    private lateinit var shopStockDatabase: ShopStockDatabaseDao
    private var currentPage = 1
    private var totalPage = 1
    private lateinit var shopStockList: List<ShopStock>
    private lateinit var shopStockListFilter: List<ShopStock>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shop_stock_list, container, false
        )

        shopStockDatabase = ShopStockDatabase.getInstance(requireContext()).shopStockDatabaseDao

        binding.shopStockOne.visibility = View.GONE
        binding.shopStockTwo.visibility = View.GONE
        binding.shopStockThree.visibility = View.GONE
        binding.shopStockFour.visibility = View.GONE
        binding.shopStockFive.visibility = View.GONE
        binding.shopStockSix.visibility = View.GONE
        binding.shopStockSeven.visibility = View.GONE
        binding.shopStockEight.visibility = View.GONE
        binding.shopStockNine.visibility = View.GONE
        binding.shopStockTen.visibility = View.GONE
        binding.buttonNextPage.visibility = View.GONE
        binding.buttonPreviousPage.visibility = View.GONE

        fetchShopStockList()

        val category = shopStockDatabase.getAllSubCategory()
        val adapterStock: ArrayAdapter<String> = ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_list_item_1,
            category
        )
        binding.category.threshold = 1
        binding.category.setAdapter(adapterStock)

        binding.category.setOnItemClickListener { parent, _, position, _ ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            currentPage = 1
            shopStockListFilter = shopStockDatabase.getListByFilter(selectedItem)
            setShopStockData()
        }

        binding.name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                shopStockListFilter = shopStockDatabase.getListByName(s.toString())
                currentPage = 1
                setShopStockData()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.low.setOnClickListener {
            shopStockListFilter = shopStockDatabase.getListLow()
            currentPage = 1
            setShopStockData()
        }
        return binding.root
    }

    private fun fetchShopStockList() {
        try {
            shopStockList = shopStockDatabase.getList()
            shopStockListFilter = shopStockList
            setShopStockData()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setShopStockData() {
        //calculating number of pages
        totalPage = shopStockListFilter.size / 10
        if (shopStockListFilter.size % 10 > 0)
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
            shopStockListFilter.size % 10

        val originalTextColor = binding.shopStockOneName.textColors
        if (numberOfItems > 0) {
            binding.shopStockOne.visibility = View.VISIBLE
            binding.shopStockOneName.text = shopStockListFilter[startingIndex].name
            binding.shopStockOneQuantity.text =
                shopStockListFilter[startingIndex].quantity.toString()
            if (shopStockListFilter[startingIndex].quantity <= shopStockListFilter[startingIndex].minQuantity) {
                binding.shopStockOneQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockOneQuantity.setTextColor(originalTextColor)
            }
            binding.shopStockOne.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockListFilter[startingIndex].id
                    )
                )
            }
        } else
            binding.shopStockOne.visibility = View.GONE
        if (numberOfItems > 1) {
            binding.shopStockTwo.visibility = View.VISIBLE
            binding.shopStockTwoName.text = shopStockListFilter[startingIndex + 1].name
            binding.shopStockTwoQuantity.text =
                shopStockListFilter[startingIndex + 1].quantity.toString()
            if (shopStockListFilter[startingIndex + 1].quantity <= shopStockListFilter[startingIndex + 1].minQuantity) {
                binding.shopStockTwoQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockTwoQuantity.setTextColor(originalTextColor)
            }
            binding.shopStockTwo.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockListFilter[startingIndex + 1].id
                    )
                )
            }
        } else
            binding.shopStockTwo.visibility = View.GONE
        if (numberOfItems > 2) {
            binding.shopStockThree.visibility = View.VISIBLE
            binding.shopStockThreeName.text = shopStockListFilter[startingIndex + 2].name
            binding.shopStockThreeQuantity.text =
                shopStockListFilter[startingIndex + 2].quantity.toString()
            if (shopStockListFilter[startingIndex + 2].quantity <= shopStockListFilter[startingIndex + 2].minQuantity) {
                binding.shopStockThreeQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockThreeQuantity.setTextColor(originalTextColor)
            }
            binding.shopStockThree.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockListFilter[startingIndex + 2].id
                    )
                )
            }
        } else
            binding.shopStockThree.visibility = View.GONE
        if (numberOfItems > 3) {
            binding.shopStockFour.visibility = View.VISIBLE
            binding.shopStockFourName.text = shopStockListFilter[startingIndex + 3].name
            binding.shopStockFourQuantity.text =
                shopStockListFilter[startingIndex + 3].quantity.toString()
            if (shopStockListFilter[startingIndex + 3].quantity <= shopStockListFilter[startingIndex + 3].minQuantity) {
                binding.shopStockFourQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockFourQuantity.setTextColor(originalTextColor)
            }
            binding.shopStockFour.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockListFilter[startingIndex + 3].id
                    )
                )
            }
        } else
            binding.shopStockFour.visibility = View.GONE
        if (numberOfItems > 4) {
            binding.shopStockFive.visibility = View.VISIBLE
            binding.shopStockFiveName.text = shopStockListFilter[startingIndex + 4].name
            binding.shopStockFiveQuantity.text =
                shopStockListFilter[startingIndex + 4].quantity.toString()
            if (shopStockListFilter[startingIndex + 4].quantity <= shopStockListFilter[startingIndex + 4].minQuantity) {
                binding.shopStockFiveQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockFiveQuantity.setTextColor(originalTextColor)
            }
            binding.shopStockFive.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockListFilter[startingIndex + 4].id
                    )
                )
            }
        } else
            binding.shopStockFive.visibility = View.GONE
        if (numberOfItems > 5) {
            binding.shopStockSix.visibility = View.VISIBLE
            binding.shopStockSixName.text = shopStockListFilter[startingIndex + 5].name
            binding.shopStockSixQuantity.text =
                shopStockListFilter[startingIndex + 5].quantity.toString()
            if (shopStockListFilter[startingIndex + 5].quantity <= shopStockListFilter[startingIndex + 5].minQuantity) {
                binding.shopStockSixQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockSixQuantity.setTextColor(originalTextColor)
            }
            binding.shopStockSix.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockListFilter[startingIndex + 5].id
                    )
                )
            }
        } else
            binding.shopStockSix.visibility = View.GONE
        if (numberOfItems > 6) {
            binding.shopStockSeven.visibility = View.VISIBLE
            binding.shopStockSevenName.text = shopStockListFilter[startingIndex + 6].name
            binding.shopStockSevenQuantity.text =
                shopStockListFilter[startingIndex + 6].quantity.toString()
            if (shopStockListFilter[startingIndex + 6].quantity <= shopStockListFilter[startingIndex + 6].minQuantity) {
                binding.shopStockSevenQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockSevenQuantity.setTextColor(originalTextColor)
            }
            binding.shopStockSeven.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockListFilter[startingIndex + 6].id
                    )
                )
            }
        } else
            binding.shopStockSeven.visibility = View.GONE
        if (numberOfItems > 7) {
            binding.shopStockEight.visibility = View.VISIBLE
            binding.shopStockEightName.text = shopStockListFilter[startingIndex + 7].name
            binding.shopStockEightQuantity.text =
                shopStockListFilter[startingIndex + 7].quantity.toString()
            if (shopStockListFilter[startingIndex + 7].quantity <= shopStockListFilter[startingIndex + 7].minQuantity) {
                binding.shopStockEightQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockEightQuantity.setTextColor(originalTextColor)
            }
            binding.shopStockEight.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockListFilter[startingIndex + 7].id
                    )
                )
            }
        } else
            binding.shopStockEight.visibility = View.GONE
        if (numberOfItems > 8) {
            binding.shopStockNine.visibility = View.VISIBLE
            binding.shopStockNineName.text = shopStockListFilter[startingIndex + 8].name
            binding.shopStockNineQuantity.text =
                shopStockListFilter[startingIndex + 8].quantity.toString()
            if (shopStockListFilter[startingIndex + 8].quantity <= shopStockListFilter[startingIndex + 8].minQuantity) {
                binding.shopStockNineQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockNineQuantity.setTextColor(originalTextColor)
            }
            binding.shopStockNine.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockListFilter[startingIndex + 8].id
                    )
                )
            }
        } else
            binding.shopStockNine.visibility = View.GONE
        if (numberOfItems > 9) {
            binding.shopStockTen.visibility = View.VISIBLE
            binding.shopStockTenName.text = shopStockListFilter[startingIndex + 9].name
            binding.shopStockTenQuantity.text =
                shopStockListFilter[startingIndex + 9].quantity.toString()
            if (shopStockListFilter[startingIndex + 9].quantity <= shopStockListFilter[startingIndex + 9].minQuantity) {
                binding.shopStockTenQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockTenQuantity.setTextColor(originalTextColor)
            }
            binding.shopStockTen.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockListFilter[startingIndex + 9].id
                    )
                )
            }
        } else
            binding.shopStockTen.visibility = View.GONE
    }
}
