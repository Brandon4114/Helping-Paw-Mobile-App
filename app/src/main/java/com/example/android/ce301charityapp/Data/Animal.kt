package com.example.android.ce301charityapp.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animals")
data class Animal (
    @PrimaryKey(autoGenerate = true)
    val ID: Int,
    val animalName: String,
    val animalDescription: String
)