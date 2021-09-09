package com.devstart.mywallet.income.repository

import com.devstart.mywallet.data.Failure
import com.devstart.mywallet.data.Response
import com.devstart.mywallet.data.Success
import com.devstart.mywallet.data.dao.IncomeDao
import com.devstart.mywallet.data.model.Income
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IncomeRepository @Inject constructor(private val incomeDao: IncomeDao) {

    suspend fun getLastIncomeTransaction() = flow<Income> {
        val data =  incomeDao.getLastIncome()
        emit(data)
    }

    fun insertIncome(income: Income) = flow<Response> {
        val data = incomeDao.insert(income)
        try {
            emit(Success(data))
        }catch (e: Throwable){
            emit(Failure(e))
        }
    }
}