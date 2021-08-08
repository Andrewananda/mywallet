package com.devstart.mywallet.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.devstart.mywallet.data.model.Expenditure
import java.sql.Date

@Dao
interface ExpenditureDao {
    @Insert
    fun insert(vararg expenditure: Expenditure): Expenditure

    @Query("SELECT * FROM expenditures WHERE date BETWEEN :startDate AND :endDate")
    fun queryExpenditure(startDate: Date, endDate: Date): List<Expenditure>

    @Query("SELECT *  FROM expenditures")
    fun getAllExpenditures(): List<Expenditure>

}