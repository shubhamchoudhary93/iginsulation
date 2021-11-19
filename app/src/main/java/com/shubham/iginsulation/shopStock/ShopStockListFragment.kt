package com.shubham.iginsulation.shopStock

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        return binding.root
    }

    private fun fetchShopStockList() {
        try {
            shopStockList = shopStockDatabase.getList()
            setShopStockData()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setShopStockData() {
        //calculating number of pages
        totalPage = shopStockList.size / 10
        if (shopStockList.size % 10 > 0)
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
            shopStockList.size % 10

        if (numberOfItems > 0) {
            binding.shopStockOne.visibility = View.VISIBLE
            binding.shopStockOneName.text = shopStockList[startingIndex].name
            binding.shopStockOneCategory.text = shopStockList[startingIndex].category
            binding.shopStockOneQuantity.text = shopStockList[startingIndex].quantity.toString()
            if (shopStockList[startingIndex].quantity <= shopStockList[startingIndex].minQuantity) {
                binding.shopStockOneQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockOneQuantity.setTextColor(Color.parseColor("#FFFFFF"))
            }
            if (shopStockList[startingIndex].quantity <= shopStockList[startingIndex].minQuantity) {
                binding.shopStockOneQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockOneQuantity.setTextColor(Color.parseColor("#FFFFFF"))
            }
            binding.shopStockOne.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockList[startingIndex].id
                    )
                )
            }
        } else
            binding.shopStockOne.visibility = View.GONE
        if (numberOfItems > 1) {
            binding.shopStockTwo.visibility = View.VISIBLE
            binding.shopStockTwoName.text = shopStockList[startingIndex + 1].name
            binding.shopStockTwoCategory.text = shopStockList[startingIndex + 1].category
            binding.shopStockTwoQuantity.text = shopStockList[startingIndex + 1].quantity.toString()
            if (shopStockList[startingIndex + 1].quantity <= shopStockList[startingIndex + 1].minQuantity) {
                binding.shopStockTwoQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockTwoQuantity.setTextColor(Color.parseColor("#FFFFFF"))
            }
            binding.shopStockTwo.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockList[startingIndex + 1].id
                    )
                )
            }
        } else
            binding.shopStockTwo.visibility = View.GONE
        if (numberOfItems > 2) {
            binding.shopStockThree.visibility = View.VISIBLE
            binding.shopStockThreeName.text = shopStockList[startingIndex + 2].name
            binding.shopStockThreeCategory.text = shopStockList[startingIndex + 2].category
            binding.shopStockThreeQuantity.text =
                shopStockList[startingIndex + 2].quantity.toString()
            if (shopStockList[startingIndex + 2].quantity <= shopStockList[startingIndex + 2].minQuantity) {
                binding.shopStockThreeQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockThreeQuantity.setTextColor(Color.parseColor("#FFFFFF"))
            }
            binding.shopStockThree.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockList[startingIndex + 2].id
                    )
                )
            }
        } else
            binding.shopStockThree.visibility = View.GONE
        if (numberOfItems > 3) {
            binding.shopStockFour.visibility = View.VISIBLE
            binding.shopStockFourName.text = shopStockList[startingIndex + 3].name
            binding.shopStockFourCategory.text = shopStockList[startingIndex + 3].category
            binding.shopStockFourQuantity.text =
                shopStockList[startingIndex + 3].quantity.toString()
            if (shopStockList[startingIndex + 3].quantity <= shopStockList[startingIndex + 3].minQuantity) {
                binding.shopStockFourQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockFourQuantity.setTextColor(Color.parseColor("#FFFFFF"))
            }
            binding.shopStockFour.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockList[startingIndex + 3].id
                    )
                )
            }
        } else
            binding.shopStockFour.visibility = View.GONE
        if (numberOfItems > 4) {
            binding.shopStockFive.visibility = View.VISIBLE
            binding.shopStockFiveName.text = shopStockList[startingIndex + 4].name
            binding.shopStockFiveCategory.text = shopStockList[startingIndex + 4].category
            binding.shopStockFiveQuantity.text =
                shopStockList[startingIndex + 4].quantity.toString()
            if (shopStockList[startingIndex + 4].quantity <= shopStockList[startingIndex + 4].minQuantity) {
                binding.shopStockFiveQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockFiveQuantity.setTextColor(Color.parseColor("#FFFFFF"))
            }
            binding.shopStockFive.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockList[startingIndex + 4].id
                    )
                )
            }
        } else
            binding.shopStockFive.visibility = View.GONE
        if (numberOfItems > 5) {
            binding.shopStockSix.visibility = View.VISIBLE
            binding.shopStockSixName.text = shopStockList[startingIndex + 5].name
            binding.shopStockSixCategory.text = shopStockList[startingIndex + 5].category
            binding.shopStockSixQuantity.text = shopStockList[startingIndex + 5].quantity.toString()
            if (shopStockList[startingIndex + 5].quantity <= shopStockList[startingIndex + 5].minQuantity) {
                binding.shopStockSixQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockSixQuantity.setTextColor(Color.parseColor("#FFFFFF"))
            }
            binding.shopStockSix.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockList[startingIndex + 5].id
                    )
                )
            }
        } else
            binding.shopStockSix.visibility = View.GONE
        if (numberOfItems > 6) {
            binding.shopStockSeven.visibility = View.VISIBLE
            binding.shopStockSevenName.text = shopStockList[startingIndex + 6].name
            binding.shopStockSevenCategory.text = shopStockList[startingIndex + 6].category
            binding.shopStockSevenQuantity.text =
                shopStockList[startingIndex + 6].quantity.toString()
            if (shopStockList[startingIndex + 6].quantity <= shopStockList[startingIndex + 6].minQuantity) {
                binding.shopStockSevenQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockSevenQuantity.setTextColor(Color.parseColor("#FFFFFF"))
            }
            binding.shopStockSeven.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockList[startingIndex + 6].id
                    )
                )
            }
        } else
            binding.shopStockSeven.visibility = View.GONE
        if (numberOfItems > 7) {
            binding.shopStockEight.visibility = View.VISIBLE
            binding.shopStockEightName.text = shopStockList[startingIndex + 7].name
            binding.shopStockEightCategory.text = shopStockList[startingIndex + 7].category
            binding.shopStockEightQuantity.text =
                shopStockList[startingIndex + 7].quantity.toString()
            if (shopStockList[startingIndex + 7].quantity <= shopStockList[startingIndex + 7].minQuantity) {
                binding.shopStockEightQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockEightQuantity.setTextColor(Color.parseColor("#FFFFFF"))
            }
            binding.shopStockEight.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockList[startingIndex + 7].id
                    )
                )
            }
        } else
            binding.shopStockEight.visibility = View.GONE
        if (numberOfItems > 8) {
            binding.shopStockNine.visibility = View.VISIBLE
            binding.shopStockNineName.text = shopStockList[startingIndex + 8].name
            binding.shopStockNineCategory.text = shopStockList[startingIndex + 8].category
            binding.shopStockNineQuantity.text =
                shopStockList[startingIndex + 8].quantity.toString()
            if (shopStockList[startingIndex + 8].quantity <= shopStockList[startingIndex + 8].minQuantity) {
                binding.shopStockNineQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockNineQuantity.setTextColor(Color.parseColor("#FFFFFF"))
            }
            binding.shopStockNine.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockList[startingIndex + 8].id
                    )
                )
            }
        } else
            binding.shopStockNine.visibility = View.GONE
        if (numberOfItems > 9) {
            binding.shopStockTen.visibility = View.VISIBLE
            binding.shopStockTenName.text = shopStockList[startingIndex + 9].name
            binding.shopStockTenCategory.text = shopStockList[startingIndex + 9].category
            binding.shopStockTenQuantity.text = shopStockList[startingIndex + 9].quantity.toString()
            if (shopStockList[startingIndex + 9].quantity <= shopStockList[startingIndex + 9].minQuantity) {
                binding.shopStockTenQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockTenQuantity.setTextColor(Color.parseColor("#FFFFFF"))
            }
            binding.shopStockTen.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockList[startingIndex + 9].id
                    )
                )
            }
        } else
            binding.shopStockTen.visibility = View.GONE
    }
}
