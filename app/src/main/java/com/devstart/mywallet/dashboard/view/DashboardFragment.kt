package com.devstart.mywallet.dashboard.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.devstart.mywallet.R
import com.devstart.mywallet.dashboard.viewModel.TransactionViewModel
import com.devstart.mywallet.data.Failure
import com.devstart.mywallet.data.Success
import com.devstart.mywallet.data.model.Transaction
import com.devstart.mywallet.databinding.FragmentDashboardBinding
import com.devstart.mywallet.utils.hide
import com.devstart.mywallet.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    @Inject
    lateinit var viewModel: TransactionViewModel

    private val sharedViewModel : SharedViewModel by activityViewModels()
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
            findNavController().navigate(R.id.action_dashboardFragment_to_passwordFragment)
        }

        sharedViewModel.showPassword.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.txtBalance.show()
                binding.btnShowBalance.hide()
            }
        })
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
            binding.txtMessage.show()
            binding.recyclerview.hide()
            binding.txtMessage.text = getString(R.string.txt_empty_transaction)
        }
    }

    private fun displayErrorDialog(error: Throwable){
        Log.i("TrnsactionError", error.localizedMessage)
    }
}