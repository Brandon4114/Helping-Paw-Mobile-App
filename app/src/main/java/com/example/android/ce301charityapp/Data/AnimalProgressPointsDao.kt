package com.example.android.ce301charityapp.Data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface AnimalProgressPointsDao {
    @Query("SELECT * FROM animalProgress")
    fun getAll(): List<AnimalProgressPoints>
}