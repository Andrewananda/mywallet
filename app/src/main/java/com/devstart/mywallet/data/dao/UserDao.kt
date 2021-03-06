package com.devstart.mywallet.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.devstart.mywallet.data.model.User

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM users where email LIKE :email AND password LIKE :password LIMIT 1")
    fun login(email: String, password: String): User
}