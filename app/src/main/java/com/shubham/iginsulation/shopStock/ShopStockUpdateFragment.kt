package com.shubham.iginsulation.shopStock

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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

        val category = shopStockDatabase.getAllSubCategory()
        val adapterStock: ArrayAdapter<String> = ArrayAdapter<String>(
            this.requireContext(),
            android.R.layout.simple_list_item_1,
            category
        )
        binding.categoryFilter.threshold = 1
        binding.categoryFilter.setAdapter(adapterStock)

        binding.categoryFilter.setOnItemClickListener { parent, _, position, _ ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            currentPage = 1
            shopStockUpdateList = shopStockDatabase.getListByFilter(selectedItem)
            setShopStockData()
        }

        binding.nameFilter.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                shopStockUpdateList = shopStockDatabase.getListByName(s.toString())
                currentPage = 1
                setShopStockData()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
        })

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
            binding.notifications.text = ""
            calculatePageData()
        }

        binding.buttonPreviousPage.setOnClickListener {
            currentPage -= 1
            binding.notifications.text = ""
            calculatePageData()
        }

        binding.buttonUpdate.setOnClickListener {
            update()
            binding.notifications.text = "Updated"
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
            binding.shopStockUpdateOneQuantity.setText(shopStockUpdateList[startingIndex].quantity.toString())
        } else
            binding.shopStockUpdateOne.visibility = View.GONE
        if (numberOfItems > 1) {
            binding.shopStockUpdateTwo.visibility = View.VISIBLE
            binding.shopStockUpdateTwoName.text = shopStockUpdateList[startingIndex + 1].name
            binding.shopStockUpdateTwoQuantity.setText(shopStockUpdateList[startingIndex + 1].quantity.toString())
        } else
            binding.shopStockUpdateTwo.visibility = View.GONE
        if (numberOfItems > 2) {
            binding.shopStockUpdateThree.visibility = View.VISIBLE
            binding.shopStockUpdateThreeName.text = shopStockUpdateList[startingIndex + 2].name
            binding.shopStockUpdateThreeQuantity.setText(shopStockUpdateList[startingIndex + 2].quantity.toString())
        } else
            binding.shopStockUpdateThree.visibility = View.GONE
        if (numberOfItems > 3) {
            binding.shopStockUpdateFour.visibility = View.VISIBLE
            binding.shopStockUpdateFourName.text = shopStockUpdateList[startingIndex + 3].name
            binding.shopStockUpdateFourQuantity.setText(shopStockUpdateList[startingIndex + 3].quantity.toString())
        } else
            binding.shopStockUpdateFour.visibility = View.GONE
        if (numberOfItems > 4) {
            binding.shopStockUpdateFive.visibility = View.VISIBLE
            binding.shopStockUpdateFiveName.text = shopStockUpdateList[startingIndex + 4].name
            binding.shopStockUpdateFiveQuantity.setText(shopStockUpdateList[startingIndex + 4].quantity.toString())
        } else
            binding.shopStockUpdateFive.visibility = View.GONE
        if (numberOfItems > 5) {
            binding.shopStockUpdateSix.visibility = View.VISIBLE
            binding.shopStockUpdateSixName.text = shopStockUpdateList[startingIndex + 5].name
            binding.shopStockUpdateSixQuantity.setText(shopStockUpdateList[startingIndex + 5].quantity.toString())
        } else
            binding.shopStockUpdateSix.visibility = View.GONE
        if (numberOfItems > 6) {
            binding.shopStockUpdateSeven.visibility = View.VISIBLE
            binding.shopStockUpdateSevenName.text = shopStockUpdateList[startingIndex + 6].name
            binding.shopStockUpdateSevenQuantity.setText(shopStockUpdateList[startingIndex + 6].quantity.toString())
        } else
            binding.shopStockUpdateSeven.visibility = View.GONE
        if (numberOfItems > 7) {
            binding.shopStockUpdateEight.visibility = View.VISIBLE
            binding.shopStockUpdateEightName.text = shopStockUpdateList[startingIndex + 7].name
            binding.shopStockUpdateEightQuantity.setText(shopStockUpdateList[startingIndex + 7].quantity.toString())
        } else
            binding.shopStockUpdateEight.visibility = View.GONE
        if (numberOfItems > 8) {
            binding.shopStockUpdateNine.visibility = View.VISIBLE
            binding.shopStockUpdateNineName.text = shopStockUpdateList[startingIndex + 8].name
            binding.shopStockUpdateNineQuantity.setText(shopStockUpdateList[startingIndex + 8].quantity.toString())
        } else
            binding.shopStockUpdateNine.visibility = View.GONE
        if (numberOfItems > 9) {
            binding.shopStockUpdateTen.visibility = View.VISIBLE
            binding.shopStockUpdateTenName.text = shopStockUpdateList[startingIndex + 9].name
            binding.shopStockUpdateTenQuantity.setText(shopStockUpdateList[startingIndex + 9].quantity.toString())
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
