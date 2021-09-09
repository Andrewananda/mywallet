package com.devstart.mywallet.dashboard.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.devstart.mywallet.R
import com.devstart.mywallet.dashboard.viewModel.TransactionViewModel
import com.devstart.mywallet.data.model.User
import com.devstart.mywallet.databinding.FragmentPasswordBinding
import com.devstart.mywallet.prefs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PasswordFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentPasswordBinding

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPasswordBinding.inflate(layoutInflater, container, false)
        binding.btnSubmit.setOnClickListener {
            checkPassword()
        }
        return binding.root
    }

    private fun checkPassword(){
        if (binding.txtPassword.text.isNullOrBlank()){
            binding.passwordLayout.error = getString(R.string.enter_password)
        }else {
             val password = binding.txtPassword.text!!.trim().toString()
            val gson = Gson()
            val user = gson.fromJson(prefs.userPref, User::class.java)

            if (user.password == password){
                sharedViewModel.shouldShow(true)
                dismiss()
            }else{
                binding.passwordLayout.error = getString(R.string.wrong_password)
            }
        }

    }
}