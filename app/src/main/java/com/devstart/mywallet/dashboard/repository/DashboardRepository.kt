package com.devstart.mywallet.dashboard.repository

import com.devstart.mywallet.data.Failure
import com.devstart.mywallet.data.Response
import com.devstart.mywallet.data.Success
import com.devstart.mywallet.data.dao.TransactionDao
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DashboardRepository @Inject constructor(private val transactionDao: TransactionDao) {
    fun fetchTransactions() = flow<Response> {
        val transactions = transactionDao.allTransactions()

        try {
            emit(Success(transactions))
        }catch (e: Throwable){
            emit(Failure(e))
        }
    }
}