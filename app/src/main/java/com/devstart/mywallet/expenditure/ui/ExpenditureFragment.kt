package com.devstart.mywallet.expenditure.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devstart.mywallet.R
import com.devstart.mywallet.databinding.FragmentExpenditureBinding

class ExpenditureFragment : Fragment() {

    private lateinit var binding: FragmentExpenditureBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentExpenditureBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}