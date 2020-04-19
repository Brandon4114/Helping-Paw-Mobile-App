package com.example.android.ce301charityapp.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.android.ce301charityapp.LOG_TAG
import com.example.android.ce301charityapp.WEB_SERVICE_URL
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class AnimalRepository(val app: Application) {

    val animalData = MutableLiveData<List<Animal>>()
    private val animalDao = AnimalDatabase.getDatabase(app).animalDao()

    val progressData = MutableLiveData<List<ProgressPoints>>()
    val progressDao = AnimalDatabase.getDatabase(app).progressDao()


    init {

        CoroutineScope(Dispatchers.IO).launch {

            callWebService()
//            val data = animalDao.getAll()
//            if (data.isEmpty()) {
//
//
//                val animalData = FileHelper.getTextFromAssets(app, "animal_data.json" )
//                parseJsonAnimalData(animalData)
//
//            } else {
//
//                animalData.postValue(data)
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(app, "Using local data", Toast.LENGTH_LONG).show()
//                }
//            }
        }
    }
//    private suspend fun parseJsonAnimalData(text:String){
//
//        val moshi = Moshi.Builder().build()
//        val adapter: JsonAdapter<List<Animal>> = moshi.adapter(listType1)
//
//        val animalData : List<Animal>? = adapter.fromJson(text)
//
//        if (animalData != null) {
//            animalDao.insertAnimals(animalData)
//        }
//
//        if (animalData != null) {
//            for (animal in animalDao.getAll()){
//                Log.i(LOG_TAG, "test ${animal.ID} | ${animal.animalName} | ${animal.animalDescription}")
//            }
//        }
//    }


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

            animalDao.deleteAll()
            progressDao.deleteAll()

            animalData.postValue(animalServiceData)
            animalDao.insertAnimals(animalServiceData)

            Log.i(LOG_TAG, "$animalServiceData")

            progressData.postValue(progressServiceData)
            progressDao.insertProgress(progressServiceData)


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