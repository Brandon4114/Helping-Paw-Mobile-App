package com.example.android.ce301charityapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.ce301charityapp.IMAGE_BASE_URL

@Entity(tableName = "animals")
data class Animal (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val animalName: String,
    val animalDescription: String,
    val imageName: String,
    val imageType: String
){
    val ImageUrl
        get()= "$IMAGE_BASE_URL/$imageName.$imageType"

}