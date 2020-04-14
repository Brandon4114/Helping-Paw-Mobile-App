package com.example.android.ce301charityapp.data

import com.example.android.ce301charityapp.WEB_SERVICE_URL
import retrofit2.Response
import retrofit2.http.GET

interface AnimalService {
    @GET(WEB_SERVICE_URL +"api/data/6")
    suspend fun getAnimalData(): Response<List<Animal>>
}