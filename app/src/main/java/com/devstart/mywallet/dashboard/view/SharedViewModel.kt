package com.devstart.mywallet.dashboard.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val showPassword = MutableLiveData<Boolean>().apply { postValue(false) }

    fun shouldShow(show: Boolean) {
        showPassword.value = show
    }
}