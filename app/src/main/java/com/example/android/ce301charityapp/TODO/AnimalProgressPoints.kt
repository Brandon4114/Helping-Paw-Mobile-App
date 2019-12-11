package com.example.android.ce301charityapp.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animalProgress")
data class AnimalProgressPoints (
    @PrimaryKey(autoGenerate = true)
    val ID: Int,
    val animalID: Int,
    val animalPoint: Int,
    val progressDescription: String
    )