package com.devstart.mywallet.expenditure.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.devstart.mywallet.R
import com.devstart.mywallet.dashboard.view.SharedViewModel
import com.devstart.mywallet.data.Failure
import com.devstart.mywallet.data.Success
import com.devstart.mywallet.databinding.FragmentExpenditureBinding
import com.devstart.mywallet.expenditure.viewModel.ExpenditureViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ExpenditureFragment : Fragment() {

    private lateinit var binding: FragmentExpenditureBinding

    @Inject
    lateinit var viewModel: ExpenditureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentExpenditureBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSubmit.setOnClickListener {
            validateInputs()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun validateInputs() {
        val amount = binding.txtAmount.text?.trim().toString()
        val description = binding.txtDescription.text?.trim().toString()

        when {
            amount.isBlank() -> {
                binding.amountLayout.error = getString(R.string.amount_required)
            }
            description.isBlank() -> {
                binding.descriptionLayout.error = getString(R.string.description_required)
            }
            else -> {
                viewModel.submitExpenditure(description, amount.toInt())
                observeSubmitResponse()
            }
        }
    }


    private fun observeSubmitResponse() {
        viewModel.expenditureResponse().observe(viewLifecycleOwner, Observer {
            when(it) {
                is Success<*> -> {
                    Toast.makeText(context, "Added successfully", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_expenditureFragment_to_dashboardFragment)
                }
                is Failure -> {
                    Log.i("ERROR", it.throwable.toString())
                }
            }
        })
    }
}