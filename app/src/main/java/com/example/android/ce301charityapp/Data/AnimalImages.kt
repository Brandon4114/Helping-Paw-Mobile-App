package com.example.android.ce301charityapp.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animalImages")
data class AnimalImages (
    @PrimaryKey(autoGenerate = false)
    val ID: Int,
    val animalID: Int,
    val imageName: String,
    val imageType:String
)