package com.example.android.ce301charityapp.data

import android.app.Application
import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.android.ce301charityapp.WEB_SERVICE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.net.URL


class AnimalRepository(val app: Application) {

    val animalData = MutableLiveData<List<Animal>>()
    private val animalDao = AnimalDatabase.getDatabase(app).animalDao()

    val progressData = MutableLiveData<List<ProgressPoints>>()
    val progressDao = AnimalDatabase.getDatabase(app).progressDao()

    val imageData = MutableLiveData<List<Images>>()
    val imageDao = AnimalDatabase.getDatabase(app).imageDao()
    init {

        CoroutineScope(Dispatchers.IO).launch {

            callWebService()

        }
    }

    @WorkerThread
    suspend fun callWebService() {
        if (networkAvailable()) {
            withContext(Dispatchers.Main) {
                Toast.makeText(app, "Using remote data", Toast.LENGTH_LONG).show()
            }

            val retrofit = Retrofit.Builder()
                .baseUrl(WEB_SERVICE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            val animalService = retrofit.create(AnimalService::class.java)
            val animalServiceData = animalService.getAnimalData().body() ?: emptyList()

            val progressService = retrofit.create(ProgressService::class.java)
            val progressServiceData = progressService.getProgressData().body() ?: emptyList()

            val imageService = retrofit.create(ImageService::class.java)
            val imageServiceData = imageService.getImageData().body() ?: emptyList()

            animalDao.deleteAll()
            progressDao.deleteAll()
            imageDao.deleteAll()

            animalData.postValue(animalServiceData)
            animalDao.insertAnimals(animalServiceData)


            progressData.postValue(progressServiceData)
            progressDao.insertProgress(progressServiceData)

            imageData.postValue(imageServiceData)
            imageDao.insertImages(imageServiceData)
        }
    }
    @Suppress("DEPRECATION")
    private fun networkAvailable(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }


    fun refreshDataFromWeb() {
        CoroutineScope(Dispatchers.IO).launch {
            callWebService()
        }
    }

}