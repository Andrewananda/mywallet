package com.devstart.mywallet.auth.signUp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
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
        // Inflate the layout for this fragment
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
        val password = binding.txtPassword.text.toString()
        val passwordConfirm = binding.txtPasswordConfirm.text.toString()

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
            password.trim().isEmpty() -> {
                binding.txtPassword.error = "Password is required"
            }
            else -> {
                viewModel.signUp(firstname, lastname, email, password)
                observeSignUpResponse()
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