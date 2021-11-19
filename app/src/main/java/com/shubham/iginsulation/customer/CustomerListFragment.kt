package com.shubham.iginsulation.customer

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shubham.iginsulation.R
import com.shubham.iginsulation.database.customer.Customer
import com.shubham.iginsulation.database.customer.CustomerDatabase
import com.shubham.iginsulation.database.customer.CustomerDatabaseDao
import com.shubham.iginsulation.databinding.FragmentCustomerListBinding

class CustomerListFragment : Fragment() {

    private lateinit var binding: FragmentCustomerListBinding
    private lateinit var customerDatabase: CustomerDatabaseDao
    private var currentPage = 1
    private var totalPage = 1
    private lateinit var customerList: List<Customer>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_customer_list, container, false
        )

        customerDatabase = CustomerDatabase.getInstance(requireContext()).customerDatabaseDao

        binding.customerOne.visibility = View.GONE
        binding.customerTwo.visibility = View.GONE
        binding.customerThree.visibility = View.GONE
        binding.customerFour.visibility = View.GONE
        binding.customerFive.visibility = View.GONE
        binding.customerSix.visibility = View.GONE
        binding.customerSeven.visibility = View.GONE
        binding.customerEight.visibility = View.GONE
        binding.customerNine.visibility = View.GONE
        binding.customerTen.visibility = View.GONE
        binding.buttonNextPage.visibility = View.GONE
        binding.buttonPreviousPage.visibility = View.GONE

        fetchCustomerList()

        return binding.root
    }

    private fun fetchCustomerList() {
        try {
            customerList = customerDatabase.getList()
            setCustomerData()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setCustomerData() {
        //calculating number of pages
        totalPage = customerList.size / 10
        if (customerList.size % 10 > 0)
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
            customerList.size % 10

        if (numberOfItems > 0) {
            binding.customerOne.visibility = View.VISIBLE
            binding.customerOneName.text = customerList[startingIndex].name
            binding.customerOneBalance.text = customerList[startingIndex].current.toString()
            if (customerList[startingIndex].current >= 0) {
                binding.customerOneBalance.setTextColor(Color.parseColor("#FFFFFF"))
            } else {
                binding.customerOneBalance.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.customerOne.setOnClickListener {
                view?.findNavController()?.navigate(
                    CustomerListFragmentDirections.actionCustomerListFragmentToCustomerDetailFragment(
                        customerList[startingIndex].id
                    )
                )
            }
        } else
            binding.customerOne.visibility = View.GONE
        if (numberOfItems > 1) {
            binding.customerTwo.visibility = View.VISIBLE
            binding.customerTwoName.text = customerList[startingIndex + 1].name
            binding.customerTwoBalance.text = customerList[startingIndex + 1].current.toString()
            if (customerList[startingIndex + 1].current >= 0) {
                binding.customerTwoBalance.setTextColor(Color.parseColor("#FFFFFF"))
            } else {
                binding.customerTwoBalance.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.customerTwo.setOnClickListener {
                view?.findNavController()?.navigate(
                    CustomerListFragmentDirections.actionCustomerListFragmentToCustomerDetailFragment(
                        customerList[startingIndex + 1].id
                    )
                )
            }
        } else
            binding.customerTwo.visibility = View.GONE
        if (numberOfItems > 2) {
            binding.customerThree.visibility = View.VISIBLE
            binding.customerThreeName.text = customerList[startingIndex + 2].name
            binding.customerThreeBalance.text = customerList[startingIndex + 2].current.toString()
            if (customerList[startingIndex + 2].current >= 0) {
                binding.customerThreeBalance.setTextColor(Color.parseColor("#FFFFFF"))
            } else {
                binding.customerThreeBalance.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.customerThree.setOnClickListener {
                view?.findNavController()?.navigate(
                    CustomerListFragmentDirections.actionCustomerListFragmentToCustomerDetailFragment(
                        customerList[startingIndex + 2].id
                    )
                )
            }
        } else
            binding.customerThree.visibility = View.GONE
        if (numberOfItems > 3) {
            binding.customerFour.visibility = View.VISIBLE
            binding.customerFourName.text = customerList[startingIndex + 3].name
            binding.customerFourBalance.text = customerList[startingIndex + 3].current.toString()
            if (customerList[startingIndex + 3].current >= 0) {
                binding.customerFourBalance.setTextColor(Color.parseColor("#FFFFFF"))
            } else {
                binding.customerFourBalance.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.customerFour.setOnClickListener {
                view?.findNavController()?.navigate(
                    CustomerListFragmentDirections.actionCustomerListFragmentToCustomerDetailFragment(
                        customerList[startingIndex + 3].id
                    )
                )
            }
        } else
            binding.customerFour.visibility = View.GONE
        if (numberOfItems > 4) {
            binding.customerFive.visibility = View.VISIBLE
            binding.customerFiveName.text = customerList[startingIndex + 4].name
            binding.customerFiveBalance.text = customerList[startingIndex + 4].current.toString()
            if (customerList[startingIndex + 4].current >= 0) {
                binding.customerFiveBalance.setTextColor(Color.parseColor("#FFFFFF"))
            } else {
                binding.customerFiveBalance.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.customerFive.setOnClickListener {
                view?.findNavController()?.navigate(
                    CustomerListFragmentDirections.actionCustomerListFragmentToCustomerDetailFragment(
                        customerList[startingIndex + 4].id
                    )
                )
            }
        } else
            binding.customerFive.visibility = View.GONE
        if (numberOfItems > 5) {
            binding.customerSix.visibility = View.VISIBLE
            binding.customerSixName.text = customerList[startingIndex + 5].name
            binding.customerSixBalance.text = customerList[startingIndex + 5].current.toString()
            if (customerList[startingIndex + 5].current >= 0) {
                binding.customerSixBalance.setTextColor(Color.parseColor("#FFFFFF"))
            } else {
                binding.customerSixBalance.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.customerSix.setOnClickListener {
                view?.findNavController()?.navigate(
                    CustomerListFragmentDirections.actionCustomerListFragmentToCustomerDetailFragment(
                        customerList[startingIndex + 5].id
                    )
                )
            }
        } else
            binding.customerSix.visibility = View.GONE
        if (numberOfItems > 6) {
            binding.customerSeven.visibility = View.VISIBLE
            binding.customerSevenName.text = customerList[startingIndex + 6].name
            binding.customerSevenBalance.text = customerList[startingIndex + 6].current.toString()
            if (customerList[startingIndex + 6].current >= 0) {
                binding.customerSevenBalance.setTextColor(Color.parseColor("#FFFFFF"))
            } else {
                binding.customerSevenBalance.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.customerSeven.setOnClickListener {
                view?.findNavController()?.navigate(
                    CustomerListFragmentDirections.actionCustomerListFragmentToCustomerDetailFragment(
                        customerList[startingIndex + 6].id
                    )
                )
            }
        } else
            binding.customerSeven.visibility = View.GONE
        if (numberOfItems > 7) {
            binding.customerEight.visibility = View.VISIBLE
            binding.customerEightName.text = customerList[startingIndex + 7].name
            binding.customerEightBalance.text = customerList[startingIndex + 7].current.toString()
            if (customerList[startingIndex + 7].current >= 0) {
                binding.customerEightBalance.setTextColor(Color.parseColor("#FFFFFF"))
            } else {
                binding.customerEightBalance.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.customerEight.setOnClickListener {
                view?.findNavController()?.navigate(
                    CustomerListFragmentDirections.actionCustomerListFragmentToCustomerDetailFragment(
                        customerList[startingIndex + 7].id
                    )
                )
            }
        } else
            binding.customerEight.visibility = View.GONE
        if (numberOfItems > 8) {
            binding.customerNine.visibility = View.VISIBLE
            binding.customerNineName.text = customerList[startingIndex + 8].name
            binding.customerNineBalance.text = customerList[startingIndex + 8].current.toString()
            if (customerList[startingIndex + 8].current >= 0) {
                binding.customerNineBalance.setTextColor(Color.parseColor("#FFFFFF"))
            } else {
                binding.customerNineBalance.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.customerNine.setOnClickListener {
                view?.findNavController()?.navigate(
                    CustomerListFragmentDirections.actionCustomerListFragmentToCustomerDetailFragment(
                        customerList[startingIndex + 8].id
                    )
                )
            }
        } else
            binding.customerNine.visibility = View.GONE
        if (numberOfItems > 9) {
            binding.customerTen.visibility = View.VISIBLE
            binding.customerTenName.text = customerList[startingIndex + 9].name
            binding.customerTenBalance.text = customerList[startingIndex + 9].current.toString()
            if (customerList[startingIndex + 9].current >= 0) {
                binding.customerTenBalance.setTextColor(Color.parseColor("#FFFFFF"))
            } else {
                binding.customerTenBalance.setTextColor(Color.parseColor("#FF0000"))
            }
            binding.customerTen.setOnClickListener {
                view?.findNavController()?.navigate(
                    CustomerListFragmentDirections.actionCustomerListFragmentToCustomerDetailFragment(
                        customerList[startingIndex + 9].id
                    )
                )
            }
        } else
            binding.customerTen.visibility = View.GONE
    }
}
