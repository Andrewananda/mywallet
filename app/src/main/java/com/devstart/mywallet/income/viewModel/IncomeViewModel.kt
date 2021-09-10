package com.devstart.mywallet.income.viewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devstart.mywallet.data.Failure
import com.devstart.mywallet.data.Response
import com.devstart.mywallet.data.Success
import com.devstart.mywallet.data.model.Income
import com.devstart.mywallet.income.repository.IncomeRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.properties.Delegates

class IncomeViewModel @Inject constructor(private val incomeRepository: IncomeRepository) : ViewModel() {
    private var lastIncome: Int = 0

    private var mutableIncome = MutableLiveData<Response>()
    fun getIncomeResponse() : LiveData<Response> = mutableIncome

    private var mutableIncomeList = MutableLiveData<Response>()
    fun incomeListResponse() : LiveData<Response> = mutableIncomeList

    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {
        checkLastIncomeTransaction()
    }

     private fun checkLastIncomeTransaction(){
        coroutineScope.launch {
            incomeRepository.getLastIncomeTransaction().collect {
                when(it) {
                    is Success<*> -> {
                        if (it.data !== null) {
                            Log.i("AmountSet", it.toString())
                            val lastAmount = it.data as Income
                            lastIncome = lastAmount.amount
                        }
                    }
                    is Failure ->{
                        Log.i("LASTINCOMEERROR", it.throwable.toString())
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun submitIncome(name: String, amount: Int) {
        coroutineScope.launch {
            val newIncome = lastIncome + amount
            val date = LocalDateTime.now().toString()
            val income = Income(0, name, newIncome, date)
            incomeRepository.insertIncome(income).collect {
                mutableIncome.postValue(it)
            }
        }
    }

    fun fetchIncomeList() {
        coroutineScope.launch {
            incomeRepository.fetchIncomeList().collect {
                mutableIncomeList.postValue(it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}
