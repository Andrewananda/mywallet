package com.devstart.mywallet.auth.signUp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devstart.mywallet.auth.signUp.repository.SignUpRepository
import com.devstart.mywallet.data.Response
import com.devstart.mywallet.data.model.User
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class SignUpViewModel @Inject constructor(private val signUpRepository: SignUpRepository) : ViewModel() {
    private val mutableSignUpResponse = MutableLiveData<Response>()
    fun signUpResponse(): LiveData<Response> = mutableSignUpResponse

    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    fun signUp(firstName: String, lastName: String, email: String,password: String) {
        val user = User(0, firstName, lastName, email, password)
        coroutineScope.launch {
            signUpRepository.signUpUser(user).collect {
                mutableSignUpResponse.postValue(it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}