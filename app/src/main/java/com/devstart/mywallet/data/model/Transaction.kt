package com.devstart.mywallet.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "amount") val amount: Int,
    @ColumnInfo(name = "initial_amount") val initialAmount: Int,
    @ColumnInfo(name = "date") val date: Date
)
