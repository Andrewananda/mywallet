package com.devstart.mywallet.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import com.devstart.mywallet.data.model.Income

@Dao
interface IncomeDao {
    @Insert
    fun insert(vararg income: Income): Income

    @Update
    fun update(vararg income: Income): Income
}