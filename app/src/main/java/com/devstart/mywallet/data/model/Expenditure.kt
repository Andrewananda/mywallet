package com.devstart.mywallet.data.model

import androidx.room.*

@Entity(tableName = "expenditures")
data class Expenditure(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "amount") val amount: Int,
    @ColumnInfo(name = "date") val date: String
)
