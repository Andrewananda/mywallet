package com.devstart.mywallet.income.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.devstart.mywallet.R
import com.devstart.mywallet.data.Failure
import com.devstart.mywallet.data.Success
import com.devstart.mywallet.data.model.Income
import com.devstart.mywallet.databinding.FragmentIncomeBinding
import com.devstart.mywallet.income.viewModel.IncomeViewModel
import com.devstart.mywallet.utils.hide
import com.devstart.mywallet.utils.show
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class IncomeFragment : Fragment() {

    private lateinit var binding: FragmentIncomeBinding
    @Inject
    lateinit var viewModel: IncomeViewModel

    private lateinit var adapter: IncomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIncomeBinding.inflate(layoutInflater, container, false)
        adapter = IncomeAdapter()
        binding.recyclerview.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchIncomeList()
        observeIncomeListResponse()
    }

    private fun observeIncomeListResponse() {
        viewModel.incomeListResponse().observe(viewLifecycleOwner, Observer {
            when(it) {
                is Success<*> -> {
                    displayIncomeList(it.data as List<Income>)
                }
                is Failure -> {
                    Log.i("INCOMEFAILURE", it.throwable.toString())
                }
            }
        })
    }

    private fun displayIncomeList(data: List<Income>) {
        if (data.isNullOrEmpty()) {
            binding.txt.show()
            binding.recyclerview.hide()
        }else {
            adapter.submitList(data)
        }
    }
}