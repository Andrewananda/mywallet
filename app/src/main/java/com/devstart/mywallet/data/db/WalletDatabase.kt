package com.devstart.mywallet.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devstart.mywallet.data.dao.*
import com.devstart.mywallet.data.model.*

@Database(entities = [User::class, Income::class, Expenditure::class, Balance::class, Transaction::class], version = 1, exportSchema = false)
abstract class WalletDatabase : RoomDatabase(){
    abstract fun userDao() : UserDao
    abstract fun incomeDao() : IncomeDao
    abstract fun expenditureDao() : ExpenditureDao
    abstract fun balanceDao() : BalanceDao
    abstract fun transactionDao() : TransactionDao
}