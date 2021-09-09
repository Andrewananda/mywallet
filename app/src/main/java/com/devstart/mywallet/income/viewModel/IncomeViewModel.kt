package com.devstart.mywallet.income.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devstart.mywallet.data.Response
import com.devstart.mywallet.data.model.Income
import com.devstart.mywallet.income.repository.IncomeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.properties.Delegates

class IncomeViewModel @Inject constructor(private val incomeRepository: IncomeRepository) : ViewModel() {
    private var lastIncome by Delegates.notNull<Int>()

    private var mutableIncome = MutableLiveData<Response>()
    fun getIncomeResponse() : LiveData<Response> = mutableIncome

    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private fun checkLastIncomeTransaction(){
        coroutineScope.launch {
            incomeRepository.getLastIncomeTransaction().collect {
                lastIncome = it.amount
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun submitIncome(name: String, amount: Int) {
        coroutineScope.launch {
            checkLastIncomeTransaction()
            var newIncome = amount
            if (lastIncome !== null) {
                newIncome = lastIncome + amount
            }
            val date = LocalDateTime.now().toString()
            val income = Income(0, name, newIncome, date)
            incomeRepository.insertIncome(income).collect {
                mutableIncome.postValue(it)
            }
        }
    }
}
