package com.devstart.mywallet.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import com.devstart.mywallet.data.model.Balance

@Dao
interface BalanceDao{
    @Insert
    fun insert(vararg balance: Balance): Balance

    @Update
    fun updateBalance(vararg balance: Balance):Balance
}
