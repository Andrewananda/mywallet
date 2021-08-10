package com.devstart.mywallet.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.devstart.mywallet.data.model.Transaction

@Dao
interface TransactionDao {
    @Insert
    fun insert(transaction: Transaction)

    @Query("SELECT * FROM transactions")
    fun allTransactions(): List<Transaction>
}