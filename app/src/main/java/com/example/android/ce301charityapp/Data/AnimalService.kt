package com.example.android.ce301charityapp.Data

import retrofit2.Response
import retrofit2.http.GET

interface AnimalService {
    @GET("/feed/monster_data.json")
    suspend fun getAnimalData(): Response<List<Animal>>
}