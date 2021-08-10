package com.devstart.mywallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.devstart.mywallet.auth.signIn.viewModel.SignInViewModel
import com.devstart.mywallet.data.Failure
import com.devstart.mywallet.data.Success
import com.devstart.mywallet.data.model.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        signInDummyUser("andrewtiema@gmail.com", "1234")
    }

    private fun signInDummyUser(email: String, password: String) {
        viewModel.loginUser(email, password)
        observeSignInResponse()
    }

    private fun observeSignInResponse() {
        viewModel.userResponse().observe(this, Observer {
            when(it) {
                is Failure -> {
                    Log.i("ErrorLoggingIn", it.throwable.toString())
                }
                is Success<*> -> {
                    if (it.data === null) {
                        Log.i("UserNotFound","User record not found")
                    }else {
                        Log.i("userData", "User Available")
                    }
                }
            }
        })
    }

    private fun loadSuccess(user: User) {
        Log.i("userData", "User Available")
    }
}