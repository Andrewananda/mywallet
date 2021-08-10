package com.devstart.mywallet.auth.signIn.repository

import com.devstart.mywallet.data.Failure
import com.devstart.mywallet.data.Response
import com.devstart.mywallet.data.Success
import com.devstart.mywallet.data.dao.UserDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignInRepository @Inject constructor(private val appService: UserDao) {

    fun signInUser(email: String, password: String) : Flow<Response> = flow {
        val loginData = appService.login(email, password)
        try {
            emit(Success(loginData))
        }catch (t: Throwable) {
            emit(Failure(t))
        }
    }
}