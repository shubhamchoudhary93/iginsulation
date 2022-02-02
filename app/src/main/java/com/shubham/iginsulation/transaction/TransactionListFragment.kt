package com.shubham.iginsulation.transaction

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.R
import com.shubham.iginsulation.database.transaction.Transaction
import com.shubham.iginsulation.database.transaction.TransactionDatabase
import com.shubham.iginsulation.database.transaction.TransactionDatabaseDao
import com.shubham.iginsulation.databinding.FragmentTransactionListBinding

class TransactionListFragment : Fragment() {

    private lateinit var binding: FragmentTransactionListBinding
    private lateinit var transactionDatabase: TransactionDatabaseDao
    private var currentPage = 1
    private var totalPage = 1
    private lateinit var transactionList: List<Transaction>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_transaction_list, container, false
        )

        transactionDatabase = TransactionDatabase.getInstance(requireContext()).transactionDatabaseDao

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

        fetchTransactionList()

        return binding.root
    }

    private fun fetchTransactionList() {
        try {
            transactionList = transactionDatabase.getList()
            setTransactionData()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setTransactionData() {
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
            binding.transactionOneName.text = transactionList[startingIndex].name
            binding.transactionOneDate.text = transactionList[startingIndex].date
            binding.transactionOneAmount.text = transactionList[startingIndex].amount.toString()
            if (transactionList[startingIndex + 1].receipt) {
                binding.transactionOneAmount.setTextColor(originalTextColor)
            } else {
                binding.transactionOneAmount.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.transactionOne.setOnClickListener {
                view?.findNavController()?.navigate(
                    TransactionListFragmentDirections.actionTransactionListFragmentToTransactionDetailFragment(
                        transactionList[startingIndex].id
                    )
                )
            }
        } else
            binding.transactionOne.visibility = View.GONE
        if (numberOfItems > 1) {
            binding.transactionTwo.visibility = View.VISIBLE
            binding.transactionTwoName.text = transactionList[startingIndex + 1].name
            binding.transactionTwoDate.text = transactionList[startingIndex + 1].date
            binding.transactionTwoAmount.text = transactionList[startingIndex + 1].amount.toString()
            if (transactionList[startingIndex + 1].receipt) {
                binding.transactionTwoAmount.setTextColor(originalTextColor)
            } else {
                binding.transactionTwoAmount.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.transactionTwo.setOnClickListener {
                view?.findNavController()?.navigate(
                    TransactionListFragmentDirections.actionTransactionListFragmentToTransactionDetailFragment(
                        transactionList[startingIndex + 1].id
                    )
                )
            }
        } else
            binding.transactionTwo.visibility = View.GONE
        if (numberOfItems > 2) {
            binding.transactionThree.visibility = View.VISIBLE
            binding.transactionThreeName.text = transactionList[startingIndex + 2].name
            binding.transactionThreeDate.text = transactionList[startingIndex + 2].date
            binding.transactionThreeAmount.text = transactionList[startingIndex + 2].amount.toString()
            if (transactionList[startingIndex + 1].receipt) {
                binding.transactionThreeAmount.setTextColor(originalTextColor)
            } else {
                binding.transactionThreeAmount.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.transactionThree.setOnClickListener {
                view?.findNavController()?.navigate(
                    TransactionListFragmentDirections.actionTransactionListFragmentToTransactionDetailFragment(
                        transactionList[startingIndex + 2].id
                    )
                )
            }
        } else
            binding.transactionThree.visibility = View.GONE
        if (numberOfItems > 3) {
            binding.transactionFour.visibility = View.VISIBLE
            binding.transactionFourName.text = transactionList[startingIndex + 3].name
            binding.transactionFourDate.text = transactionList[startingIndex + 3].date
            binding.transactionFourAmount.text = transactionList[startingIndex + 3].amount.toString()
            if (transactionList[startingIndex + 1].receipt) {
                binding.transactionFourAmount.setTextColor(originalTextColor)
            } else {
                binding.transactionFourAmount.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.transactionFour.setOnClickListener {
                view?.findNavController()?.navigate(
                    TransactionListFragmentDirections.actionTransactionListFragmentToTransactionDetailFragment(
                        transactionList[startingIndex + 3].id
                    )
                )
            }
        } else
            binding.transactionFour.visibility = View.GONE
        if (numberOfItems > 4) {
            binding.transactionFive.visibility = View.VISIBLE
            binding.transactionFiveName.text = transactionList[startingIndex + 4].name
            binding.transactionFiveDate.text = transactionList[startingIndex + 4].date
            binding.transactionFiveAmount.text = transactionList[startingIndex + 4].amount.toString()
            if (transactionList[startingIndex + 1].receipt) {
                binding.transactionFiveAmount.setTextColor(originalTextColor)
            } else {
                binding.transactionFiveAmount.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.transactionFive.setOnClickListener {
                view?.findNavController()?.navigate(
                    TransactionListFragmentDirections.actionTransactionListFragmentToTransactionDetailFragment(
                        transactionList[startingIndex + 4].id
                    )
                )
            }
        } else
            binding.transactionFive.visibility = View.GONE
        if (numberOfItems > 5) {
            binding.transactionSix.visibility = View.VISIBLE
            binding.transactionSixName.text = transactionList[startingIndex + 5].name
            binding.transactionSixDate.text = transactionList[startingIndex + 5].date
            binding.transactionSixAmount.text = transactionList[startingIndex + 5].amount.toString()
            if (transactionList[startingIndex + 1].receipt) {
                binding.transactionSixAmount.setTextColor(originalTextColor)
            } else {
                binding.transactionSixAmount.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.transactionSix.setOnClickListener {
                view?.findNavController()?.navigate(
                    TransactionListFragmentDirections.actionTransactionListFragmentToTransactionDetailFragment(
                        transactionList[startingIndex + 5].id
                    )
                )
            }
        } else
            binding.transactionSix.visibility = View.GONE
        if (numberOfItems > 6) {
            binding.transactionSeven.visibility = View.VISIBLE
            binding.transactionSevenName.text = transactionList[startingIndex + 6].name
            binding.transactionSevenDate.text = transactionList[startingIndex + 6].date
            binding.transactionSevenAmount.text = transactionList[startingIndex + 6].amount.toString()
            if (transactionList[startingIndex + 1].receipt) {
                binding.transactionSevenAmount.setTextColor(originalTextColor)
            } else {
                binding.transactionSevenAmount.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.transactionSeven.setOnClickListener {
                view?.findNavController()?.navigate(
                    TransactionListFragmentDirections.actionTransactionListFragmentToTransactionDetailFragment(
                        transactionList[startingIndex + 6].id
                    )
                )
            }
        } else
            binding.transactionSeven.visibility = View.GONE
        if (numberOfItems > 7) {
            binding.transactionEight.visibility = View.VISIBLE
            binding.transactionEightName.text = transactionList[startingIndex + 7].name
            binding.transactionEightDate.text = transactionList[startingIndex + 7].date
            binding.transactionEightAmount.text = transactionList[startingIndex + 7].amount.toString()
            if (transactionList[startingIndex + 1].receipt) {
                binding.transactionEightAmount.setTextColor(originalTextColor)
            } else {
                binding.transactionEightAmount.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.transactionEight.setOnClickListener {
                view?.findNavController()?.navigate(
                    TransactionListFragmentDirections.actionTransactionListFragmentToTransactionDetailFragment(
                        transactionList[startingIndex + 7].id
                    )
                )
            }
        } else
            binding.transactionEight.visibility = View.GONE
        if (numberOfItems > 8) {
            binding.transactionNine.visibility = View.VISIBLE
            binding.transactionNineName.text = transactionList[startingIndex + 8].name
            binding.transactionNineDate.text = transactionList[startingIndex + 8].date
            binding.transactionNineAmount.text = transactionList[startingIndex + 8].amount.toString()
            if (transactionList[startingIndex + 1].receipt) {
                binding.transactionNineAmount.setTextColor(originalTextColor)
            } else {
                binding.transactionNineAmount.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.transactionNine.setOnClickListener {
                view?.findNavController()?.navigate(
                    TransactionListFragmentDirections.actionTransactionListFragmentToTransactionDetailFragment(
                        transactionList[startingIndex + 8].id
                    )
                )
            }
        } else
            binding.transactionNine.visibility = View.GONE
        if (numberOfItems > 9) {
            binding.transactionTen.visibility = View.VISIBLE
            binding.transactionTenName.text = transactionList[startingIndex + 9].name
            binding.transactionTenDate.text = transactionList[startingIndex + 9].date
            binding.transactionTenAmount.text = transactionList[startingIndex + 9].amount.toString()
            if (transactionList[startingIndex + 1].receipt) {
                binding.transactionTenAmount.setTextColor(originalTextColor)
            } else {
                binding.transactionTenAmount.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.transactionTen.setOnClickListener {
                view?.findNavController()?.navigate(
                    TransactionListFragmentDirections.actionTransactionListFragmentToTransactionDetailFragment(
                        transactionList[startingIndex + 9].id
                    )
                )
            }
        } else
            binding.transactionTen.visibility = View.GONE
    }
}
