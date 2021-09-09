package com.devstart.mywallet.expenditure.repository

import com.devstart.mywallet.data.Failure
import com.devstart.mywallet.data.Response
import com.devstart.mywallet.data.Success
import com.devstart.mywallet.data.dao.ExpenditureDao
import com.devstart.mywallet.data.model.Expenditure
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ExpenditureRepository @Inject constructor(private val expenditureDao: ExpenditureDao) {

    fun postExpenditure(expenditure: Expenditure) = flow<Response> {
        val data =  expenditureDao.insert(expenditure)
        try {
            emit(Success(data))
        }catch (e: Throwable) {
            emit(Failure(e))
        }
    }
}