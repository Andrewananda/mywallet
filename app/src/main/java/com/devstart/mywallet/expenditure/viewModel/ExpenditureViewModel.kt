package com.devstart.mywallet.expenditure.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devstart.mywallet.data.Response
import com.devstart.mywallet.data.model.Expenditure
import com.devstart.mywallet.expenditure.repository.ExpenditureRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import java.time.LocalDateTime
import javax.inject.Inject

class ExpenditureViewModel @Inject constructor(private val expenditureRepository: ExpenditureRepository) : ViewModel() {
    private var expenditureResponse = MutableLiveData<Response>()
    fun expenditureResponse() : LiveData<Response> = expenditureResponse

    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    @RequiresApi(Build.VERSION_CODES.O)
    fun submitExpenditure(name: String, amount: Int) {
        val date = LocalDateTime.now().toString()
        val data = Expenditure(0, name, amount, date)
        coroutineScope.launch {
            expenditureRepository.postExpenditure(data).collect {
                expenditureResponse.postValue(it)
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}