package com.devstart.mywallet.utils

import android.view.View
import java.text.DecimalFormat
import java.text.NumberFormat

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun numberFormat(number: Double): String {
    val formatter: NumberFormat = DecimalFormat("#,###")
    return formatter.format(number)
}