package com.shubham.iginsulation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.shubham.iginsulation.BackupRestore.backup
import com.shubham.iginsulation.BackupRestore.restore
import com.shubham.iginsulation.database.customer.CustomerDatabase
import com.shubham.iginsulation.database.shopstock.ShopStockDatabase
import com.shubham.iginsulation.database.stock.StockDatabase
import com.shubham.iginsulation.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private var firebaseAuth: FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_start, container, false
        )

        firebaseAuth = FirebaseAuth.getInstance()
        setListeners()
        if (firebaseAuth!!.currentUser == null) {
            binding.loginLayout.visibility = View.VISIBLE
            binding.buttonsLayout.visibility = View.GONE
        } else {
            binding.loginLayout.visibility = View.GONE
            binding.buttonsLayout.visibility = View.VISIBLE
        }
        return binding.root
    }

    private fun setListeners() {

        binding.login.setOnClickListener {
            var username = binding.username.text.toString()
            var password = binding.password.text.toString()
            if (username == "") username = "app"
            if (password == "") password = "gg"
            firebaseAuth!!.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        binding.loginLayout.visibility = View.GONE
                        binding.buttonsLayout.visibility = View.VISIBLE
                    } else {
                        Toast.makeText(
                            context,
                            "Login failed",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("shubhamchoudhary", task.exception.toString())
                    }
                }
        }
        binding.buttonCustomer.setOnClickListener {
            view?.findNavController()
                ?.navigate(StartFragmentDirections.actionStartFragmentToCustomerFragment())
        }

        binding.buttonStock.setOnClickListener {
            view?.findNavController()
                ?.navigate(StartFragmentDirections.actionStartFragmentToStockFragment())
        }

        binding.buttonSale.setOnClickListener {
            view?.findNavController()
                ?.navigate(StartFragmentDirections.actionStartFragmentToSaleFragment())
        }

        binding.buttonTransaction.setOnClickListener {
            view?.findNavController()
                ?.navigate(StartFragmentDirections.actionStartFragmentToTransactionFragment())
        }

        binding.buttonRateList.setOnClickListener {
            view?.findNavController()
                ?.navigate(StartFragmentDirections.actionStartFragmentToRateListFragment())
        }

        binding.buttonShopStock.setOnClickListener {
            view?.findNavController()
                ?.navigate(StartFragmentDirections.actionStartFragmentToShopStockFragment())
        }

        binding.buttonCommittee.setOnClickListener {
            view?.findNavController()
                ?.navigate(StartFragmentDirections.actionStartFragmentToCommitteeFragment())
        }

        binding.buttonDummy.setOnClickListener {

            val customerDatabase =
                CustomerDatabase.getInstance(requireContext()).customerDatabaseDao
            val stockDatabase = StockDatabase.getInstance(requireContext()).stockDatabaseDao
            val shopStockDatabase =
                ShopStockDatabase.getInstance(requireContext()).shopStockDatabaseDao

            DummyData.populate(customerDatabase, stockDatabase, shopStockDatabase)
        }

        binding.buttonBackup.setOnClickListener {
            backup(requireContext())
        }

        binding.buttonRestore.setOnClickListener {
            restore(requireContext())
        }
    }
}