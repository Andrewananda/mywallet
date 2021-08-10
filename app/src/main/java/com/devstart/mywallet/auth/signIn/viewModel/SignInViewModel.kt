package com.devstart.mywallet.auth.signIn.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devstart.mywallet.auth.signIn.repository.SignInRepository
import com.devstart.mywallet.data.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val signInRepository: SignInRepository): ViewModel() {
    private val mutableUserResponse = MutableLiveData<Response>()
    fun userResponse() : LiveData<Response> = mutableUserResponse

    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    fun loginUser(email: String, password: String) {
        coroutineScope.launch {
            signInRepository.signInUser(email, password).collect {
                mutableUserResponse.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}