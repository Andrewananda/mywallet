package com.devstart.mywallet.di

import android.content.Context
import androidx.room.Room
import com.devstart.mywallet.data.dao.*
import com.devstart.mywallet.data.db.WalletDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideUserDao(appDatabase: WalletDatabase) : UserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideBalanceDao(appDatabase: WalletDatabase) : BalanceDao {
        return appDatabase.balanceDao()
    }

    @Provides
    @Singleton
    fun provideExpenditureDao(appDatabase: WalletDatabase): ExpenditureDao{
        return appDatabase.expenditureDao()
    }

    @Provides
    @Singleton
    fun provideIncomeDao(appDatabase: WalletDatabase): IncomeDao {
        return appDatabase.incomeDao()
    }

    @Provides
    @Singleton
    fun provideTransactionDao(appDatabase: WalletDatabase): TransactionDao {
        return appDatabase.transactionDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context) : WalletDatabase {
        return Room.databaseBuilder(
            appContext,
            WalletDatabase::class.java,
            "wallet"
        ).build()
    }
}