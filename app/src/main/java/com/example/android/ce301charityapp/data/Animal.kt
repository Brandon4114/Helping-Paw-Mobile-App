package com.example.android.ce301charityapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animals")
data class Animal (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val animalName: String,
    val animalDescription: String
)