package com.devstart.mywallet.utils

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {
    private val USER = "user"
    private val preferences: SharedPreferences = context.getSharedPreferences(USER, Context.MODE_PRIVATE)

    var userPref: String?
    get() = preferences.getString(USER, "")
    set(value) = preferences.edit().putString(USER, value).apply()
}

