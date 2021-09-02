package com.devstart.mywallet.auth.signIn.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devstart.mywallet.auth.signIn.repository.SignInRepository
import com.devstart.mywallet.data.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val signInRepository: SignInRepository): ViewModel() {
    private val mutableUserResponse = MutableLiveData<Response>()
    fun userResponse() : LiveData<Response> = mutableUserResponse

    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun loginUser(email: String, password: String) {
        Log.i("Email" , email)
        Log.i("Password", password)
        coroutineScope.launch {
            signInRepository.signInUser(email, password).collect {
                mutableUserResponse.postValue(it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}