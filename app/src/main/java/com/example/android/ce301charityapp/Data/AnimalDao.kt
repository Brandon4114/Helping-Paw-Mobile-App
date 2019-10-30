package com.example.android.ce301charityapp.Data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface AnimalDao {

    @Query("SELECT * FROM animals")
    fun getAll(): List<Animal>

}