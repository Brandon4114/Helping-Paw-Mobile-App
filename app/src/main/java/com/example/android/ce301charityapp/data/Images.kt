package com.example.android.ce301charityapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.ce301charityapp.IMAGE_BASE_URL

@Entity(tableName = "Images")
data class Images (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val animalID: Int,
    val ImageName: String,
    val imageType:String
) {
    val ImageUrl
        get()= "$IMAGE_BASE_URL/$ImageName.$imageType"

}