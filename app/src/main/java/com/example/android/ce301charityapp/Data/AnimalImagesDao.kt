package com.example.android.ce301charityapp.Data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface AnimalImagesDao {
    @Query("SELECT * from animalImages")
    fun getAll(): List<AnimalImages>
}