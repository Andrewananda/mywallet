package com.devstart.mywallet.income.view

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.devstart.mywallet.R
import com.devstart.mywallet.data.Failure
import com.devstart.mywallet.data.Success
import com.devstart.mywallet.databinding.FragmentAddIncomeBinding
import com.devstart.mywallet.databinding.FragmentIncomeBinding
import com.devstart.mywallet.income.viewModel.IncomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddIncomeFragment : DialogFragment() {

    private lateinit var binding: FragmentAddIncomeBinding

    @Inject
    lateinit var incomeViewModel: IncomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddIncomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            this.dismiss()
        }

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
                incomeViewModel.submitIncome(description, amount.toInt())
                observeIncomeSubmitResponse()
            }
        }
    }

    private fun observeIncomeSubmitResponse() {
        incomeViewModel.getIncomeResponse().observe(viewLifecycleOwner, Observer {
            when(it) {
                is Success<*> -> {
                    Toast.makeText(context, "Submitted successfully", Toast.LENGTH_LONG).show()
                    dismiss()
                }
                is Failure -> {
                    Log.i("Error", it.throwable.toString())
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }
    }

    companion object {
        const val TAG = "AddIncomeFragmentDialog"
    }
}