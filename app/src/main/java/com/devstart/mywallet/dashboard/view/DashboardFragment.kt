package com.devstart.mywallet.dashboard.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.devstart.mywallet.R
import com.devstart.mywallet.dashboard.viewModel.TransactionViewModel
import com.devstart.mywallet.data.Failure
import com.devstart.mywallet.data.Success
import com.devstart.mywallet.data.model.Transaction
import com.devstart.mywallet.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    @Inject
    lateinit var viewModel: TransactionViewModel
    private lateinit var adapter: TransactionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        viewModel.fetchUserTransactions()
        observeUserTransactions()
        adapter = TransactionsAdapter()
        binding.recyclerview.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnShowBalance.setOnClickListener {
//            binding.txtBalance.visibility = View.VISIBLE
//            binding.btnShowBalance.visibility = View.GONE
            findNavController().navigate(R.id.action_dashboardFragment_to_passwordFragment)
        }
    }

    private fun observeUserTransactions() {
        viewModel.getTransactions().observe(viewLifecycleOwner, Observer {
            when(it) {
                is Success<*>-> {
                    displayRecentTransactions(it.data as List<Transaction>)
                }
                is Failure -> {
                    displayErrorDialog(it.throwable)
                }
            }
        })
    }

    private fun displayRecentTransactions(data: List<Transaction>) {
        if (!data.isNullOrEmpty()) {
            adapter.submitList(data)
        }else{
            binding.txtMessage.visibility = View.VISIBLE
            binding.recyclerview.visibility = View.GONE
            binding.txtMessage.text = getString(R.string.txt_empty_transaction)
        }
    }

    private fun displayErrorDialog(error: Throwable){
        Log.i("TrnsactionError", error.localizedMessage)
    }
}