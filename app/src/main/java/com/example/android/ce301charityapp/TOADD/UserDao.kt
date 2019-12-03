package com.example.android.ce301charityapp.TOADD

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Insert
    suspend fun insertUsers(users: List<User>)

    @Query("DELETE from user")
    suspend fun deleteAll()
}