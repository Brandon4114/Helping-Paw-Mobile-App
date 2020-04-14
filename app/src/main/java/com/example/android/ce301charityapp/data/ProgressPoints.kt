package com.example.android.ce301charityapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Progress")
data class ProgressPoints (
    @PrimaryKey(autoGenerate = true)
    val ID: Int,
    val animalID: Int,
    val animalPoint: Int,
    val progressDescription: String
    )