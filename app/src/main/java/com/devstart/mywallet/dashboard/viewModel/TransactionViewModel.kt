package com.devstart.mywallet.dashboard.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devstart.mywallet.dashboard.repository.DashboardRepository
import com.devstart.mywallet.data.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class TransactionViewModel @Inject constructor(private val transactionRepository: DashboardRepository) : ViewModel() {

    private val transactions = MutableLiveData<Response>()
    fun getTransactions(): LiveData<Response> = transactions

    private val mutableShowBalance = MutableLiveData<Boolean>().apply { postValue(false) }
    fun showBalance() : LiveData<Boolean> = mutableShowBalance

    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun fetchUserTransactions() {
        coroutineScope.launch {
            transactionRepository.fetchTransactions().collect {
                transactions.postValue(it)
            }
        }
    }

    fun enableShowBalance(show: Boolean) {
        mutableShowBalance.value = true
        Log.i("Enable", show.toString())
    }
}