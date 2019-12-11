package com.example.android.ce301charityapp.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AnimalProgressPointsDao {
    @Query("SELECT * FROM animalProgress")
    fun getAll(): List<AnimalProgressPoints>

    @Insert
    suspend fun insertAnimalProgress(animals: List<AnimalProgressPoints>)

    @Query("DELETE from animalProgress")
    suspend fun deleteAll()
}