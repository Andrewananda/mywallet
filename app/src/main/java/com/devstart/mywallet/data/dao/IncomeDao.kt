package com.devstart.mywallet.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.devstart.mywallet.data.model.Income

@Dao
interface IncomeDao {
    @Insert
    fun insert(income: Income)

    @Update
    fun update(income: Income)

    @Query("SELECT * FROM incomes ORDER BY ID DESC LIMIT 1")
    fun getLastIncome() : Income
}