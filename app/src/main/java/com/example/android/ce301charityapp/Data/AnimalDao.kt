package com.example.android.ce301charityapp.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AnimalDao {

    @Query("SELECT * FROM animals")
    fun getAll(): List<Animal>

    @Insert
    suspend fun insertAnimals(monsters: List<Animal>)

    @Query("DELETE from animals")
    suspend fun deleteAll()
}