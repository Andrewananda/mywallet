package com.devstart.mywallet

import android.app.Application
import com.devstart.mywallet.utils.Prefs
import dagger.hilt.android.HiltAndroidApp

val prefs: Prefs by lazy {
    MyWallet.prefs!!
}

@HiltAndroidApp
open class MyWallet : Application() {

    companion object {
        var prefs: Prefs? = null
        lateinit var instance: MyWallet
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        prefs = Prefs(applicationContext)
    }
}