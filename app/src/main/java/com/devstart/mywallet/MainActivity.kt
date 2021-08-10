package com.devstart.mywallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.devstart.mywallet.auth.signIn.viewModel.SignInViewModel
import com.devstart.mywallet.auth.signUp.viewModel.SignUpViewModel
import com.devstart.mywallet.data.Failure
import com.devstart.mywallet.data.Success
import com.devstart.mywallet.data.model.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}