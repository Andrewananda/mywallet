package com.devstart.mywallet.auth.signIn.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devstart.mywallet.data.dao.UserDao
import com.devstart.mywallet.data.model.User
import javax.inject.Inject

class SignInRepository @Inject constructor(private val appService: UserDao) {
    private var mutableUser = MutableLiveData<User>()
    val userLiveData: LiveData<User>
    get() = mutableUser

    suspend fun signInUser(email: String, password: String) : User {
        val loginData = appService.login(email, password)
        mutableUser.value = loginData
        return loginData
    }
}