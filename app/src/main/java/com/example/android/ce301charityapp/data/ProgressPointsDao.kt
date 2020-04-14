package com.example.android.ce301charityapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProgressPointsDao {
    @Query("SELECT * FROM Progress")
    fun getAll(): List<ProgressPoints>

    @Insert
    suspend fun insertProgress(animals: List<ProgressPoints>)

    @Query("DELETE from Progress")
    suspend fun deleteAll()

    @Query ("SELECT * FROM Progress WHERE animalPoint = :Selected ")
    public abstract  fun progress(int: Selected): List<ProgressPoints>
}