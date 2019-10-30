package com.example.android.ce301charityapp.Data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User (
    @PrimaryKey(autoGenerate = true)
    val ID: Int,
    val name: String,
    val userName: String,
    val password: String
)