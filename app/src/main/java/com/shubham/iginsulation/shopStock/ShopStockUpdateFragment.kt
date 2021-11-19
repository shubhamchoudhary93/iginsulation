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
import com.shubham.iginsulation.databinding.FragmentShopStockUpdateBinding

class ShopStockUpdateFragment : Fragment() {

    private lateinit var binding: FragmentShopStockUpdateBinding
    private lateinit var shopStockDatabase: ShopStockDatabaseDao
    private var currentPage = 1
    private var totalPage = 1
    private lateinit var shopStockUpdateList: List<ShopStock>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shop_stock_update, container, false
        )

        shopStockDatabase = ShopStockDatabase.getInstance(requireContext()).shopStockDatabaseDao

        binding.shopStockUpdateOne.visibility = View.GONE
        binding.shopStockUpdateTwo.visibility = View.GONE
        binding.shopStockUpdateThree.visibility = View.GONE
        binding.shopStockUpdateFour.visibility = View.GONE
        binding.shopStockUpdateFive.visibility = View.GONE
        binding.shopStockUpdateSix.visibility = View.GONE
        binding.shopStockUpdateSeven.visibility = View.GONE
        binding.shopStockUpdateEight.visibility = View.GONE
        binding.shopStockUpdateNine.visibility = View.GONE
        binding.shopStockUpdateTen.visibility = View.GONE
        binding.buttonNextPage.visibility = View.GONE
        binding.buttonPreviousPage.visibility = View.GONE

        fetchShopStockList()

        return binding.root
    }

    private fun fetchShopStockList() {
        try {
            shopStockUpdateList = shopStockDatabase.getList()
            setShopStockData()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setShopStockData() {
        //calculating number of pages
        totalPage = shopStockUpdateList.size / 10
        if (shopStockUpdateList.size % 10 > 0)
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

        binding.buttonUpdate.setOnClickListener {
            update()
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
            shopStockUpdateList.size % 10

        if (numberOfItems > 0) {
            binding.shopStockUpdateOne.visibility = View.VISIBLE
            binding.shopStockUpdateOneName.text = shopStockUpdateList[startingIndex].name
            binding.shopStockUpdateOneSubCategory.text = shopStockUpdateList[startingIndex].subCategory
            binding.shopStockUpdateOneQuantity.setText(shopStockUpdateList[startingIndex].quantity.toString())
            if (shopStockUpdateList[startingIndex].quantity <= shopStockUpdateList[startingIndex].minQuantity) {
                binding.shopStockUpdateOneQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockUpdateOneQuantity.setTextColor(Color.parseColor("#FFFFFF"))
            }
            if (shopStockUpdateList[startingIndex].quantity <= shopStockUpdateList[startingIndex].minQuantity) {
                binding.shopStockUpdateOneQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockUpdateOneQuantity.setTextColor(Color.parseColor("#FFFFFF"))
            }
            binding.shopStockUpdateOne.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockUpdateList[startingIndex].id
                    )
                )
            }
        } else
            binding.shopStockUpdateOne.visibility = View.GONE
        if (numberOfItems > 1) {
            binding.shopStockUpdateTwo.visibility = View.VISIBLE
            binding.shopStockUpdateTwoName.text = shopStockUpdateList[startingIndex + 1].name
            binding.shopStockUpdateTwoSubCategory.text = shopStockUpdateList[startingIndex + 1].subCategory
            binding.shopStockUpdateTwoQuantity.setText(shopStockUpdateList[startingIndex + 1].quantity.toString())
            if (shopStockUpdateList[startingIndex + 1].quantity <= shopStockUpdateList[startingIndex + 1].minQuantity) {
                binding.shopStockUpdateTwoQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockUpdateTwoQuantity.setTextColor(Color.parseColor("#FFFFFF"))
            }
            binding.shopStockUpdateTwo.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockUpdateList[startingIndex + 1].id
                    )
                )
            }
        } else
            binding.shopStockUpdateTwo.visibility = View.GONE
        if (numberOfItems > 2) {
            binding.shopStockUpdateThree.visibility = View.VISIBLE
            binding.shopStockUpdateThreeName.text = shopStockUpdateList[startingIndex + 2].name
            binding.shopStockUpdateThreeSubCategory.text = shopStockUpdateList[startingIndex + 2].subCategory
            binding.shopStockUpdateThreeQuantity.setText(shopStockUpdateList[startingIndex + 2].quantity.toString())
            if (shopStockUpdateList[startingIndex + 2].quantity <= shopStockUpdateList[startingIndex + 2].minQuantity) {
                binding.shopStockUpdateThreeQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockUpdateThreeQuantity.setTextColor(Color.parseColor("#FFFFFF"))
            }
            binding.shopStockUpdateThree.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockUpdateList[startingIndex + 2].id
                    )
                )
            }
        } else
            binding.shopStockUpdateThree.visibility = View.GONE
        if (numberOfItems > 3) {
            binding.shopStockUpdateFour.visibility = View.VISIBLE
            binding.shopStockUpdateFourName.text = shopStockUpdateList[startingIndex + 3].name
            binding.shopStockUpdateFourSubCategory.text = shopStockUpdateList[startingIndex + 3].subCategory
            binding.shopStockUpdateFourQuantity.setText(shopStockUpdateList[startingIndex + 3].quantity.toString())
            if (shopStockUpdateList[startingIndex + 3].quantity <= shopStockUpdateList[startingIndex + 3].minQuantity) {
                binding.shopStockUpdateFourQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockUpdateFourQuantity.setTextColor(Color.parseColor("#FFFFFF"))
            }
            binding.shopStockUpdateFour.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockUpdateList[startingIndex + 3].id
                    )
                )
            }
        } else
            binding.shopStockUpdateFour.visibility = View.GONE
        if (numberOfItems > 4) {
            binding.shopStockUpdateFive.visibility = View.VISIBLE
            binding.shopStockUpdateFiveName.text = shopStockUpdateList[startingIndex + 4].name
            binding.shopStockUpdateFiveSubCategory.text = shopStockUpdateList[startingIndex + 4].subCategory
            binding.shopStockUpdateFiveQuantity.setText(shopStockUpdateList[startingIndex + 4].quantity.toString())
            if (shopStockUpdateList[startingIndex + 4].quantity <= shopStockUpdateList[startingIndex + 4].minQuantity) {
                binding.shopStockUpdateFiveQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockUpdateFiveQuantity.setTextColor(Color.parseColor("#FFFFFF"))
            }
            binding.shopStockUpdateFive.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockUpdateList[startingIndex + 4].id
                    )
                )
            }
        } else
            binding.shopStockUpdateFive.visibility = View.GONE
        if (numberOfItems > 5) {
            binding.shopStockUpdateSix.visibility = View.VISIBLE
            binding.shopStockUpdateSixName.text = shopStockUpdateList[startingIndex + 5].name
            binding.shopStockUpdateSixSubCategory.text = shopStockUpdateList[startingIndex + 5].subCategory
            binding.shopStockUpdateSixQuantity.setText(shopStockUpdateList[startingIndex + 5].quantity.toString())
            if (shopStockUpdateList[startingIndex + 5].quantity <= shopStockUpdateList[startingIndex + 5].minQuantity) {
                binding.shopStockUpdateSixQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockUpdateSixQuantity.setTextColor(Color.parseColor("#FFFFFF"))
            }
            binding.shopStockUpdateSix.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockUpdateList[startingIndex + 5].id
                    )
                )
            }
        } else
            binding.shopStockUpdateSix.visibility = View.GONE
        if (numberOfItems > 6) {
            binding.shopStockUpdateSeven.visibility = View.VISIBLE
            binding.shopStockUpdateSevenName.text = shopStockUpdateList[startingIndex + 6].name
            binding.shopStockUpdateSevenSubCategory.text = shopStockUpdateList[startingIndex + 6].subCategory
            binding.shopStockUpdateSevenQuantity.setText(shopStockUpdateList[startingIndex + 6].quantity.toString())
            if (shopStockUpdateList[startingIndex + 6].quantity <= shopStockUpdateList[startingIndex + 6].minQuantity) {
                binding.shopStockUpdateSevenQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockUpdateSevenQuantity.setTextColor(Color.parseColor("#FFFFFF"))
            }
            binding.shopStockUpdateSeven.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockUpdateList[startingIndex + 6].id
                    )
                )
            }
        } else
            binding.shopStockUpdateSeven.visibility = View.GONE
        if (numberOfItems > 7) {
            binding.shopStockUpdateEight.visibility = View.VISIBLE
            binding.shopStockUpdateEightName.text = shopStockUpdateList[startingIndex + 7].name
            binding.shopStockUpdateEightSubCategory.text = shopStockUpdateList[startingIndex + 7].subCategory
            binding.shopStockUpdateEightQuantity.setText(shopStockUpdateList[startingIndex + 7].quantity.toString())
            if (shopStockUpdateList[startingIndex + 7].quantity <= shopStockUpdateList[startingIndex + 7].minQuantity) {
                binding.shopStockUpdateEightQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockUpdateEightQuantity.setTextColor(Color.parseColor("#FFFFFF"))
            }
            binding.shopStockUpdateEight.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockUpdateList[startingIndex + 7].id
                    )
                )
            }
        } else
            binding.shopStockUpdateEight.visibility = View.GONE
        if (numberOfItems > 8) {
            binding.shopStockUpdateNine.visibility = View.VISIBLE
            binding.shopStockUpdateNineName.text = shopStockUpdateList[startingIndex + 8].name
            binding.shopStockUpdateNineSubCategory.text = shopStockUpdateList[startingIndex + 8].subCategory
            binding.shopStockUpdateNineQuantity.setText(shopStockUpdateList[startingIndex + 8].quantity.toString())
            if (shopStockUpdateList[startingIndex + 8].quantity <= shopStockUpdateList[startingIndex + 8].minQuantity) {
                binding.shopStockUpdateNineQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockUpdateNineQuantity.setTextColor(Color.parseColor("#FFFFFF"))
            }
            binding.shopStockUpdateNine.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockUpdateList[startingIndex + 8].id
                    )
                )
            }
        } else
            binding.shopStockUpdateNine.visibility = View.GONE
        if (numberOfItems > 9) {
            binding.shopStockUpdateTen.visibility = View.VISIBLE
            binding.shopStockUpdateTenName.text = shopStockUpdateList[startingIndex + 9].name
            binding.shopStockUpdateTenSubCategory.text = shopStockUpdateList[startingIndex + 9].subCategory
            binding.shopStockUpdateTenQuantity.setText(shopStockUpdateList[startingIndex + 9].quantity.toString())
            if (shopStockUpdateList[startingIndex + 9].quantity <= shopStockUpdateList[startingIndex + 9].minQuantity) {
                binding.shopStockUpdateTenQuantity.setTextColor(Color.parseColor("#FF0000"))
            } else {
                binding.shopStockUpdateTenQuantity.setTextColor(Color.parseColor("#FFFFFF"))
            }
            binding.shopStockUpdateTen.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockListFragmentDirections.actionShopStockListFragmentToShopStockDetailFragment(
                        shopStockUpdateList[startingIndex + 9].id
                    )
                )
            }
        } else
            binding.shopStockUpdateTen.visibility = View.GONE
    }

    private fun update() {
        val startingIndex = (currentPage - 1) * 10

        val numberOfItems: Int = if (totalPage > currentPage)
            10
        else
            shopStockUpdateList.size % 10

        if (numberOfItems > 0) {
            val shopStockUpdate = shopStockUpdateList[startingIndex]
            shopStockUpdate.quantity = binding.shopStockUpdateOneQuantity.text.toString().toIntOrNull() ?: 0
            shopStockDatabase.update(shopStockUpdate)
        }
        if (numberOfItems > 1) {
            val shopStockUpdate = shopStockUpdateList[startingIndex + 1]
            shopStockUpdate.quantity = binding.shopStockUpdateTwoQuantity.text.toString().toIntOrNull() ?: 0
            shopStockDatabase.update(shopStockUpdate)
        }
        if (numberOfItems > 2) {
            val shopStockUpdate = shopStockUpdateList[startingIndex + 2]
            shopStockUpdate.quantity = binding.shopStockUpdateThreeQuantity.text.toString().toIntOrNull() ?: 0
            shopStockDatabase.update(shopStockUpdate)
        }
        if (numberOfItems > 3) {
            val shopStockUpdate = shopStockUpdateList[startingIndex + 3]
            shopStockUpdate.quantity = binding.shopStockUpdateFourQuantity.text.toString().toIntOrNull() ?: 0
            shopStockDatabase.update(shopStockUpdate)
        }
        if (numberOfItems > 4) {
            val shopStockUpdate = shopStockUpdateList[startingIndex + 4]
            shopStockUpdate.quantity = binding.shopStockUpdateFiveQuantity.text.toString().toIntOrNull() ?: 0
            shopStockDatabase.update(shopStockUpdate)
        }
        if (numberOfItems > 5) {
            val shopStockUpdate = shopStockUpdateList[startingIndex + 5]
            shopStockUpdate.quantity = binding.shopStockUpdateSixQuantity.text.toString().toIntOrNull() ?: 0
            shopStockDatabase.update(shopStockUpdate)
        }
        if (numberOfItems > 6) {
            val shopStockUpdate = shopStockUpdateList[startingIndex + 6]
            shopStockUpdate.quantity = binding.shopStockUpdateSevenQuantity.text.toString().toIntOrNull() ?: 0
            shopStockDatabase.update(shopStockUpdate)
        }
        if (numberOfItems > 7) {
            val shopStockUpdate = shopStockUpdateList[startingIndex + 7]
            shopStockUpdate.quantity = binding.shopStockUpdateEightQuantity.text.toString().toIntOrNull() ?: 0
            shopStockDatabase.update(shopStockUpdate)
        }
        if (numberOfItems > 8) {
            val shopStockUpdate = shopStockUpdateList[startingIndex + 8]
            shopStockUpdate.quantity = binding.shopStockUpdateNineQuantity.text.toString().toIntOrNull() ?: 0
            shopStockDatabase.update(shopStockUpdate)
        }
        if (numberOfItems > 9) {
            val shopStockUpdate = shopStockUpdateList[startingIndex + 9]
            shopStockUpdate.quantity = binding.shopStockUpdateTenQuantity.text.toString().toIntOrNull() ?: 0
            shopStockDatabase.update(shopStockUpdate)
        }
    }
}
