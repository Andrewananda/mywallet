package com.devstart.mywallet.auth.signUp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.devstart.mywallet.R
import com.devstart.mywallet.auth.signUp.viewModel.SignUpViewModel
import com.devstart.mywallet.data.Failure
import com.devstart.mywallet.data.Success
import com.devstart.mywallet.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    @Inject
     lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        binding.txtSignin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        binding.btnSubmit.setOnClickListener {
            signUpUser()
        }

        return binding.root
    }


    private fun signUpUser() {
        val firstname = binding.firstName.text.toString()
        val lastname = binding.txtLastName.text.toString()
        val email = binding.txtEmail.text.toString()
        val password = binding.txtPassword.text?.trim().toString()
        val passwordConfirm = binding.txtPasswordConfirm.text?.trim().toString()

        when {
            firstname.trim().isEmpty() -> {
                binding.firstName.error = "First name is required"
            }
            lastname.trim().isEmpty() -> {
                binding.txtLastName.error = "Last name is required"
            }
            email.trim().isEmpty() -> {
                binding.txtEmail.error = "Email is required"
            }
            password.isEmpty() -> {
                binding.txtPassword.error = "Password is required"
            }

            else -> {
                if (password == passwordConfirm) {
                    viewModel.signUp(firstname, lastname, email, password)
                    observeSignUpResponse()
                }else {
                    binding.txtPassword.error = "Password do not match"
                    binding.txtPasswordConfirm.error = "Password do not match"
                }
            }
        }
    }

    private fun observeSignUpResponse() {
        viewModel.signUpResponse().observe(viewLifecycleOwner, Observer {
            when(it){
                is Success<*> -> {
                    Toast.makeText(context, "Added successfully", Toast.LENGTH_LONG).show()
                }
                is Failure -> {
                    Toast.makeText(context, "An error occurred", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}