package com.shubham.iginsulation.rateList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.shubham.iginsulation.R
import com.shubham.iginsulation.databinding.FragmentChinaBinding
import com.shubham.iginsulation.databinding.FragmentCottonBinding
import kotlin.math.roundToInt

class CottonFragment : Fragment() {

    private lateinit var binding: FragmentCottonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_cotton, container, false
        )

        return binding.root
    }

}