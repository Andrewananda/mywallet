package com.devstart.mywallet.auth.signUp.repository

import com.devstart.mywallet.data.Failure
import com.devstart.mywallet.data.Response
import com.devstart.mywallet.data.Success
import com.devstart.mywallet.data.dao.UserDao
import com.devstart.mywallet.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignUpRepository @Inject constructor(private val appService: UserDao) {
    fun signUpUser(user: User): Flow<Response> = flow {
        val values = appService.insert(user)
        try {
            emit(Success(values))
        }catch (t: Throwable) {
            emit(Failure(t))
        }
    }
}