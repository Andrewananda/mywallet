package com.devstart.mywallet.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.devstart.mywallet.data.model.Expenditure

@Dao
interface ExpenditureDao {
    @Insert
    fun insert(expenditure: Expenditure)

    @Query("SELECT * FROM expenditures WHERE date BETWEEN :startDate AND :endDate")
    fun queryExpenditure(startDate: String, endDate: String): List<Expenditure>

    @Query("SELECT *  FROM expenditures")
    fun getAllExpenditures(): List<Expenditure>

}