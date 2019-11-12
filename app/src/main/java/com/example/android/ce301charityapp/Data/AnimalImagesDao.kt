package com.example.android.ce301charityapp.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AnimalImagesDao {
    @Query("SELECT * from animalImages")
    fun getAll(): List<AnimalImages>

    @Insert
    suspend fun insertAnimalImages(monsters: List<AnimalImages>)

    @Query("DELETE from animalImages")
    suspend fun deleteAll()
}