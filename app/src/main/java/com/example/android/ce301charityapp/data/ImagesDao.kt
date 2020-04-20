package com.example.android.ce301charityapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ImagesDao {
    @Query("SELECT * from Images")
    fun getAll(): List<Images>

    @Insert
    suspend fun insertImages(images: List<Images>)

    @Query("DELETE from Images")
    suspend fun deleteAll()

    @Query("SELECT * FROM Images WHERE animalID = :slectedAnimal")
    fun selectedImages(slectedAnimal: Int): Array<Images>
}