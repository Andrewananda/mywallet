package com.devstart.mywallet.auth.signIn.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.devstart.mywallet.R
import com.devstart.mywallet.auth.signIn.viewModel.SignInViewModel
import com.devstart.mywallet.data.Failure
import com.devstart.mywallet.data.Response
import com.devstart.mywallet.data.Success
import com.devstart.mywallet.data.model.User
import com.devstart.mywallet.databinding.FragmentSignInBinding
import com.devstart.mywallet.databinding.FragmentSignUpBinding
import com.devstart.mywallet.prefs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding

    @Inject
    lateinit var viewModel: SignInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        binding.txtSignup.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
        binding.btnSubmit.setOnClickListener {
            signIn()
        }
        return binding.root
    }

    private fun signIn() {
        when{
            binding.txtEmail.text?.trim().toString().isEmpty() -> {
                binding.emailLayout.error = "Email field is required"
            }
            binding.txtPassword.text?.trim().toString().isEmpty() -> {
                binding.passwordLayout.error = "Password field is required"
            }
            else -> {
                viewModel.loginUser(binding.txtEmail.text.toString().trim(), binding.txtPassword.text.toString().trim())
                observeSignInResponse()
            }
        }
    }

    private fun observeSignInResponse() {
        viewModel.userResponse().observe(viewLifecycleOwner, Observer {
            when(it){
                is Success<*> -> {
                    if (it.data != null){
                        logSuccess(it.data as User)
                    }else {
                        Toast.makeText(context, "Account does not exist", Toast.LENGTH_LONG).show()
                    }
                }
                is Failure -> {
                    Toast.makeText(context, "Wrong email or password", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun logSuccess(data: User) {
        prefs.userPref = data.toString()
        findNavController().navigate(R.id.action_signInFragment_to_dashboardFragment)
    }
}