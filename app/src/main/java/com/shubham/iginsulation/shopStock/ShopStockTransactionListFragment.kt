package com.shubham.iginsulation.shopStock

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.R
import com.shubham.iginsulation.database.shopStockTransaction.ShopStockTransaction
import com.shubham.iginsulation.database.shopStockTransaction.ShopStockTransactionDatabase
import com.shubham.iginsulation.database.shopStockTransaction.ShopStockTransactionDatabaseDao
import com.shubham.iginsulation.databinding.FragmentShopStockTransactionListBinding
import java.util.*

class ShopStockTransactionListFragment : Fragment() {

    private lateinit var binding: FragmentShopStockTransactionListBinding
    private lateinit var shopStockTransactionDatabase: ShopStockTransactionDatabaseDao
    private var currentPage = 1
    private var totalPage = 1
    private lateinit var transactionList: List<ShopStockTransaction>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shop_stock_transaction_list, container, false
        )

        shopStockTransactionDatabase =
            ShopStockTransactionDatabase.getInstance(requireContext()).shopStockTransactionDatabaseDao

        binding.transactionOne.visibility = View.GONE
        binding.transactionTwo.visibility = View.GONE
        binding.transactionThree.visibility = View.GONE
        binding.transactionFour.visibility = View.GONE
        binding.transactionFive.visibility = View.GONE
        binding.transactionSix.visibility = View.GONE
        binding.transactionSeven.visibility = View.GONE
        binding.transactionEight.visibility = View.GONE
        binding.transactionNine.visibility = View.GONE
        binding.transactionTen.visibility = View.GONE
        binding.buttonNextPage.visibility = View.GONE
        binding.buttonPreviousPage.visibility = View.GONE

        fetchShopStockTransactionList()

        binding.name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                transactionList = shopStockTransactionDatabase.getListByName(s.toString())
                currentPage = 1
                setShopStockTransactionData()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.date.setOnClickListener {

            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this.requireContext(),
                { _, yearPick, monthOfYear, dayOfMonth ->
                    val text = dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + yearPick
                    binding.date.setText(text)
                    transactionList =
                        shopStockTransactionDatabase.getByDate(text) as List<ShopStockTransaction>
                    currentPage = 1
                    setShopStockTransactionData()
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        return binding.root
    }

    private fun fetchShopStockTransactionList() {
        try {
            transactionList = shopStockTransactionDatabase.getList()
            setShopStockTransactionData()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setShopStockTransactionData() {
        //calculating number of pages
        totalPage = transactionList.size / 10
        if (transactionList.size % 10 > 0)
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
            transactionList.size % 10
        val originalTextColor = binding.transactionOneName.textColors
        if (numberOfItems > 0) {
            binding.transactionOne.visibility = View.VISIBLE
            binding.transactionOneName.text = transactionList[startingIndex].stock
            binding.transactionOneDate.text = transactionList[startingIndex].date
            binding.transactionOneQuantity.text = transactionList[startingIndex].quantity.toString()
            if (transactionList[startingIndex].add) {
                binding.transactionOneQuantity.setTextColor(originalTextColor)
            } else {
                binding.transactionOneQuantity.setTextColor(Color.parseColor("#FF0000"))
            }
            if (transactionList[startingIndex].add) {
                binding.transactionOneQuantity.setTextColor(originalTextColor)
            } else {
                binding.transactionOneQuantity.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.transactionOne.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockTransactionListFragmentDirections.actionShopStockTransactionListFragmentToShopStockTransactionDetailFragment(
                        transactionList[startingIndex].id
                    )
                )
            }
        } else
            binding.transactionOne.visibility = View.GONE
        if (numberOfItems > 1) {
            binding.transactionTwo.visibility = View.VISIBLE
            binding.transactionTwoName.text = transactionList[startingIndex + 1].stock
            binding.transactionTwoDate.text = transactionList[startingIndex + 1].date
            binding.transactionTwoQuantity.text =
                transactionList[startingIndex + 1].quantity.toString()
            if (transactionList[startingIndex + 1].add) {
                binding.transactionTwoQuantity.setTextColor(originalTextColor)
            } else {
                binding.transactionTwoQuantity.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.transactionTwo.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockTransactionListFragmentDirections.actionShopStockTransactionListFragmentToShopStockTransactionDetailFragment(
                        transactionList[startingIndex + 1].id
                    )
                )
            }
        } else
            binding.transactionTwo.visibility = View.GONE
        if (numberOfItems > 2) {
            binding.transactionThree.visibility = View.VISIBLE
            binding.transactionThreeName.text = transactionList[startingIndex + 2].stock
            binding.transactionThreeDate.text = transactionList[startingIndex + 2].date
            binding.transactionThreeQuantity.text =
                transactionList[startingIndex + 2].quantity.toString()
            if (transactionList[startingIndex + 2].add) {
                binding.transactionThreeQuantity.setTextColor(originalTextColor)
            } else {
                binding.transactionThreeQuantity.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.transactionThree.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockTransactionListFragmentDirections.actionShopStockTransactionListFragmentToShopStockTransactionDetailFragment(
                        transactionList[startingIndex + 2].id
                    )
                )
            }
        } else
            binding.transactionThree.visibility = View.GONE
        if (numberOfItems > 3) {
            binding.transactionFour.visibility = View.VISIBLE
            binding.transactionFourName.text = transactionList[startingIndex + 3].stock
            binding.transactionFourDate.text = transactionList[startingIndex + 3].date
            binding.transactionFourQuantity.text =
                transactionList[startingIndex + 3].quantity.toString()
            if (transactionList[startingIndex + 3].add) {
                binding.transactionFourQuantity.setTextColor(originalTextColor)
            } else {
                binding.transactionFourQuantity.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.transactionFour.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockTransactionListFragmentDirections.actionShopStockTransactionListFragmentToShopStockTransactionDetailFragment(
                        transactionList[startingIndex + 3].id
                    )
                )
            }
        } else
            binding.transactionFour.visibility = View.GONE
        if (numberOfItems > 4) {
            binding.transactionFive.visibility = View.VISIBLE
            binding.transactionFiveName.text = transactionList[startingIndex + 4].stock
            binding.transactionFiveDate.text = transactionList[startingIndex + 4].date
            binding.transactionFiveQuantity.text =
                transactionList[startingIndex + 4].quantity.toString()
            if (transactionList[startingIndex + 4].add) {
                binding.transactionFiveQuantity.setTextColor(originalTextColor)
            } else {
                binding.transactionFiveQuantity.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.transactionFive.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockTransactionListFragmentDirections.actionShopStockTransactionListFragmentToShopStockTransactionDetailFragment(
                        transactionList[startingIndex + 4].id
                    )
                )
            }
        } else
            binding.transactionFive.visibility = View.GONE
        if (numberOfItems > 5) {
            binding.transactionSix.visibility = View.VISIBLE
            binding.transactionSixName.text = transactionList[startingIndex + 5].stock
            binding.transactionSixDate.text = transactionList[startingIndex + 5].date
            binding.transactionSixQuantity.text =
                transactionList[startingIndex + 5].quantity.toString()
            if (transactionList[startingIndex + 5].add) {
                binding.transactionSixQuantity.setTextColor(originalTextColor)
            } else {
                binding.transactionSixQuantity.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.transactionSix.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockTransactionListFragmentDirections.actionShopStockTransactionListFragmentToShopStockTransactionDetailFragment(
                        transactionList[startingIndex + 5].id
                    )
                )
            }
        } else
            binding.transactionSix.visibility = View.GONE
        if (numberOfItems > 6) {
            binding.transactionSeven.visibility = View.VISIBLE
            binding.transactionSevenName.text = transactionList[startingIndex + 6].stock
            binding.transactionSevenDate.text = transactionList[startingIndex + 6].date
            binding.transactionSevenQuantity.text =
                transactionList[startingIndex + 6].quantity.toString()
            if (transactionList[startingIndex + 6].add) {
                binding.transactionSevenQuantity.setTextColor(originalTextColor)
            } else {
                binding.transactionSevenQuantity.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.transactionSeven.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockTransactionListFragmentDirections.actionShopStockTransactionListFragmentToShopStockTransactionDetailFragment(
                        transactionList[startingIndex + 6].id
                    )
                )
            }
        } else
            binding.transactionSeven.visibility = View.GONE
        if (numberOfItems > 7) {
            binding.transactionEight.visibility = View.VISIBLE
            binding.transactionEightName.text = transactionList[startingIndex + 7].stock
            binding.transactionEightDate.text = transactionList[startingIndex + 7].date
            binding.transactionEightQuantity.text =
                transactionList[startingIndex + 7].quantity.toString()
            if (transactionList[startingIndex + 7].add) {
                binding.transactionEightQuantity.setTextColor(originalTextColor)
            } else {
                binding.transactionEightQuantity.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.transactionEight.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockTransactionListFragmentDirections.actionShopStockTransactionListFragmentToShopStockTransactionDetailFragment(
                        transactionList[startingIndex + 7].id
                    )
                )
            }
        } else
            binding.transactionEight.visibility = View.GONE
        if (numberOfItems > 8) {
            binding.transactionNine.visibility = View.VISIBLE
            binding.transactionNineName.text = transactionList[startingIndex + 8].stock
            binding.transactionNineDate.text = transactionList[startingIndex + 8].date
            binding.transactionNineQuantity.text =
                transactionList[startingIndex + 8].quantity.toString()
            if (transactionList[startingIndex + 8].add) {
                binding.transactionNineQuantity.setTextColor(originalTextColor)
            } else {
                binding.transactionNineQuantity.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.transactionNine.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockTransactionListFragmentDirections.actionShopStockTransactionListFragmentToShopStockTransactionDetailFragment(
                        transactionList[startingIndex + 8].id
                    )
                )
            }
        } else
            binding.transactionNine.visibility = View.GONE
        if (numberOfItems > 9) {
            binding.transactionTen.visibility = View.VISIBLE
            binding.transactionTenName.text = transactionList[startingIndex + 9].stock
            binding.transactionTenDate.text = transactionList[startingIndex + 9].date
            binding.transactionTenQuantity.text =
                transactionList[startingIndex + 9].quantity.toString()
            if (transactionList[startingIndex + 9].add) {
                binding.transactionTenQuantity.setTextColor(originalTextColor)
            } else {
                binding.transactionTenQuantity.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.transactionTen.setOnClickListener {
                view?.findNavController()?.navigate(
                    ShopStockTransactionListFragmentDirections.actionShopStockTransactionListFragmentToShopStockTransactionDetailFragment(
                        transactionList[startingIndex + 9].id
                    )
                )
            }
        } else
            binding.transactionTen.visibility = View.GONE
    }
}
