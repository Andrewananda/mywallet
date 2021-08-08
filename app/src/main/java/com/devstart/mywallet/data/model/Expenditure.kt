package com.devstart.mywallet.data.model

import androidx.room.*
import java.sql.Date

@Entity(tableName = "expenditures")
data class Expenditure(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @Embedded val userId: User,
    @Relation(parentColumn = "userId", entityColumn = "user_id")
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "amount") val amount: Int,
    @ColumnInfo(name = "date") val date: Date
)
