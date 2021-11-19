package com.shubham.iginsulation.sale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.R
import com.shubham.iginsulation.database.sale.Sale
import com.shubham.iginsulation.database.sale.SaleDatabase
import com.shubham.iginsulation.database.sale.SaleDatabaseDao
import com.shubham.iginsulation.databinding.FragmentSaleListBinding

class SaleListFragment : Fragment() {

    private lateinit var binding: FragmentSaleListBinding
    private lateinit var saleDatabase: SaleDatabaseDao
    private var currentPage = 1
    private var totalPage = 1
    private lateinit var saleList: List<Sale>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_sale_list, container, false
        )

        saleDatabase = SaleDatabase.getInstance(requireContext()).saleDatabaseDao

        binding.saleOne.visibility = View.GONE
        binding.saleTwo.visibility = View.GONE
        binding.saleThree.visibility = View.GONE
        binding.saleFour.visibility = View.GONE
        binding.saleFive.visibility = View.GONE
        binding.saleSix.visibility = View.GONE
        binding.saleSeven.visibility = View.GONE
        binding.saleEight.visibility = View.GONE
        binding.saleNine.visibility = View.GONE
        binding.saleTen.visibility = View.GONE
        binding.buttonNextPage.visibility = View.GONE
        binding.buttonPreviousPage.visibility = View.GONE

        fetchSaleList()

        return binding.root
    }

    private fun fetchSaleList() {
        try {
            saleList = saleDatabase.getList()
            setSaleData()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setSaleData() {
        //calculating number of pages
        totalPage = saleList.size / 10
        if (saleList.size % 10 > 0)
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
            saleList.size % 10

        if (numberOfItems > 0) {
            binding.saleOne.visibility = View.VISIBLE
            binding.saleOneName.text = saleList[startingIndex].name
            binding.saleOneDate.text = saleList[startingIndex].date
            binding.saleOneAmount.text = saleList[startingIndex].amount.toString()
            binding.saleOne.setOnClickListener {
                view?.findNavController()?.navigate(
                    SaleListFragmentDirections.actionSaleListFragmentToSaleDetailFragment(
                        saleList[startingIndex].id
                    )
                )
            }
        } else
            binding.saleOne.visibility = View.GONE
        if (numberOfItems > 1) {
            binding.saleTwo.visibility = View.VISIBLE
            binding.saleTwoName.text = saleList[startingIndex + 1].name
            binding.saleTwoDate.text = saleList[startingIndex + 1].date
            binding.saleTwoAmount.text = saleList[startingIndex + 1].amount.toString()
            binding.saleTwo.setOnClickListener {
                view?.findNavController()?.navigate(
                    SaleListFragmentDirections.actionSaleListFragmentToSaleDetailFragment(
                        saleList[startingIndex + 1].id
                    )
                )
            }
        } else
            binding.saleTwo.visibility = View.GONE
        if (numberOfItems > 2) {
            binding.saleThree.visibility = View.VISIBLE
            binding.saleThreeName.text = saleList[startingIndex + 2].name
            binding.saleThreeDate.text = saleList[startingIndex + 2].date
            binding.saleThreeAmount.text = saleList[startingIndex + 2].amount.toString()
            binding.saleThree.setOnClickListener {
                view?.findNavController()?.navigate(
                    SaleListFragmentDirections.actionSaleListFragmentToSaleDetailFragment(
                        saleList[startingIndex + 2].id
                    )
                )
            }
        } else
            binding.saleThree.visibility = View.GONE
        if (numberOfItems > 3) {
            binding.saleFour.visibility = View.VISIBLE
            binding.saleFourName.text = saleList[startingIndex + 3].name
            binding.saleFourDate.text = saleList[startingIndex + 3].date
            binding.saleFourAmount.text = saleList[startingIndex + 3].amount.toString()
            binding.saleFour.setOnClickListener {
                view?.findNavController()?.navigate(
                    SaleListFragmentDirections.actionSaleListFragmentToSaleDetailFragment(
                        saleList[startingIndex + 3].id
                    )
                )
            }
        } else
            binding.saleFour.visibility = View.GONE
        if (numberOfItems > 4) {
            binding.saleFive.visibility = View.VISIBLE
            binding.saleFiveName.text = saleList[startingIndex + 4].name
            binding.saleFiveDate.text = saleList[startingIndex + 4].date
            binding.saleFiveAmount.text = saleList[startingIndex + 4].amount.toString()
            binding.saleFive.setOnClickListener {
                view?.findNavController()?.navigate(
                    SaleListFragmentDirections.actionSaleListFragmentToSaleDetailFragment(
                        saleList[startingIndex + 4].id
                    )
                )
            }
        } else
            binding.saleFive.visibility = View.GONE
        if (numberOfItems > 5) {
            binding.saleSix.visibility = View.VISIBLE
            binding.saleSixName.text = saleList[startingIndex + 5].name
            binding.saleSixDate.text = saleList[startingIndex + 5].date
            binding.saleSixAmount.text = saleList[startingIndex + 5].amount.toString()
            binding.saleSix.setOnClickListener {
                view?.findNavController()?.navigate(
                    SaleListFragmentDirections.actionSaleListFragmentToSaleDetailFragment(
                        saleList[startingIndex + 5].id
                    )
                )
            }
        } else
            binding.saleSix.visibility = View.GONE
        if (numberOfItems > 6) {
            binding.saleSeven.visibility = View.VISIBLE
            binding.saleSevenName.text = saleList[startingIndex + 6].name
            binding.saleSevenDate.text = saleList[startingIndex + 6].date
            binding.saleSevenAmount.text = saleList[startingIndex + 6].amount.toString()
            binding.saleSeven.setOnClickListener {
                view?.findNavController()?.navigate(
                    SaleListFragmentDirections.actionSaleListFragmentToSaleDetailFragment(
                        saleList[startingIndex + 6].id
                    )
                )
            }
        } else
            binding.saleSeven.visibility = View.GONE
        if (numberOfItems > 7) {
            binding.saleEight.visibility = View.VISIBLE
            binding.saleEightName.text = saleList[startingIndex + 7].name
            binding.saleEightDate.text = saleList[startingIndex + 7].date
            binding.saleEightAmount.text = saleList[startingIndex + 7].amount.toString()
            binding.saleEight.setOnClickListener {
                view?.findNavController()?.navigate(
                    SaleListFragmentDirections.actionSaleListFragmentToSaleDetailFragment(
                        saleList[startingIndex + 7].id
                    )
                )
            }
        } else
            binding.saleEight.visibility = View.GONE
        if (numberOfItems > 8) {
            binding.saleNine.visibility = View.VISIBLE
            binding.saleNineName.text = saleList[startingIndex + 8].name
            binding.saleNineDate.text = saleList[startingIndex + 8].date
            binding.saleNineAmount.text = saleList[startingIndex + 8].amount.toString()
            binding.saleNine.setOnClickListener {
                view?.findNavController()?.navigate(
                    SaleListFragmentDirections.actionSaleListFragmentToSaleDetailFragment(
                        saleList[startingIndex + 8].id
                    )
                )
            }
        } else
            binding.saleNine.visibility = View.GONE
        if (numberOfItems > 9) {
            binding.saleTen.visibility = View.VISIBLE
            binding.saleTenName.text = saleList[startingIndex + 9].name
            binding.saleTenDate.text = saleList[startingIndex + 9].date
            binding.saleTenAmount.text = saleList[startingIndex + 9].amount.toString()
            binding.saleTen.setOnClickListener {
                view?.findNavController()?.navigate(
                    SaleListFragmentDirections.actionSaleListFragmentToSaleDetailFragment(
                        saleList[startingIndex + 9].id
                    )
                )
            }
        } else
            binding.saleTen.visibility = View.GONE
    }
}
