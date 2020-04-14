package com.example.android.ce301charityapp.data

import com.example.android.ce301charityapp.WEB_SERVICE_URL
import retrofit2.Response
import retrofit2.http.GET

interface ProgressService {
    @GET(WEB_SERVICE_URL +"api/progress/6")
    suspend fun getProgressData(): Response<List<ProgressPoints>>
}