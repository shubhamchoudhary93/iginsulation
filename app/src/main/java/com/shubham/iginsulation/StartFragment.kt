package com.shubham.iginsulation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.shubham.iginsulation.BackupRestore.restore
import com.shubham.iginsulation.databinding.FragmentStartBinding


class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private var firebaseAuth: FirebaseAuth? = null
//    private lateinit var data: SharedPreferences

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
//        data = requireActivity().getSharedPreferences("IGInsulation", Context.MODE_PRIVATE)

//        if (data.contains("DataUpdateRequired")) {
//            val prefsEditor: SharedPreferences.Editor = data.edit()
//            prefsEditor.putString("DataUpdateRequired", "0")
//            prefsEditor.apply()
//        }

        if (firebaseAuth!!.currentUser == null) {
            binding.loginLayout.visibility = View.VISIBLE
            binding.restoreLayout.visibility = View.GONE
        } else {
            binding.loginLayout.visibility = View.GONE
            binding.restoreLayout.visibility = View.VISIBLE
            restore(context, binding)
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
                        binding.restoreLayout.visibility = View.VISIBLE
                        restore(context, binding)
                    } else {
                        Toast.makeText(
                            context,
                            "Login failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }

        binding.buttonBilling.setOnClickListener {
            view?.findNavController()
                ?.navigate(StartFragmentDirections.actionStartFragmentToBillingFragment())
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

        binding.buttonSettings.setOnClickListener {
            view?.findNavController()
                ?.navigate(StartFragmentDirections.actionStartFragmentToSettingsFragment())
        }
    }
}