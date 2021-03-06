package com.devstart.mywallet.income.repository

import com.devstart.mywallet.data.Failure
import com.devstart.mywallet.data.Response
import com.devstart.mywallet.data.Success
import com.devstart.mywallet.data.dao.IncomeDao
import com.devstart.mywallet.data.model.Income
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IncomeRepository @Inject constructor(private val incomeDao: IncomeDao) {

    suspend fun getLastIncomeTransaction() = flow {
        val data =  incomeDao.getLastIncome()
        try {
            emit(Success(data))
        }catch (e: Throwable) {
            emit(Failure(e))
        }
    }

    fun insertIncome(income: Income) = flow<Response> {
        val data = incomeDao.insert(income)
        try {
            emit(Success(data))
        }catch (e: Throwable){
            emit(Failure(e))
        }
    }

    fun fetchIncomeList() = flow<Response> {
        val incomeList = incomeDao.fetchAllIncomes()
        try {
            emit(Success(incomeList))
        }catch (e: Throwable) {
            emit(Failure(e))
        }
    }
}