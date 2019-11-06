package com.example.android.ce301charityapp.Data

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.example.android.ce301charityapp.utilities.FileHelper
import com.example.androiddata.LOG_TAG
import com.example.androiddata.WEB_SERVICE_URL
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
    private val animaldao = AnimalDatabase.getDatabase(app).animalDao()

    private val listType = Types.newParameterizedType(
        List::class.java, Animal::class.java
    )


    init {
        CoroutineScope(Dispatchers.IO).launch {
            val data = animaldao.getAll()
            if (data.isEmpty()) {
//                callWebService()

                val data = FileHelper.getTextFromAssets(app, "animal_data.json" )
                parseJsonFileText(data)


            } else {
                animalData.postValue(data)
                withContext(Dispatchers.Main) {
                    Toast.makeText(app, "Using local data", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    suspend fun parseJsonFileText(text:String){

        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<List<Animal>> = moshi.adapter(listType)
        val animalData : List<Animal>? = adapter.fromJson(text)

        //animalData.postValue(data)
        animaldao.deleteAll()
        if (animalData != null) {
            animaldao.insertAnimals(animalData)
        }

        for (animal in animalData ?: emptyList()){
           Log.i(LOG_TAG, "${animal.animalName} ${animal.animalDescription}")
       }



    }

    // Update when using my webpage to get data
    @WorkerThread
    suspend fun callWebService() {
        if (networkAvailable()) {
            withContext(Dispatchers.Main) {
                Toast.makeText(app, "Using remote data", Toast.LENGTH_LONG).show()
            }
            Log.i(LOG_TAG, "Calling web service")
            val retrofit = Retrofit.Builder()
                .baseUrl(WEB_SERVICE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            val service = retrofit.create(AnimalService::class.java)
            val serviceData = service.getAnimalData().body() ?: emptyList()
//            animalData.postValue(serviceData)
//            animaldao.deleteAll()
//            animaldao.insertAnimals(serviceData)
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

    private fun saveDataToCache(animalData: List<Animal>) {
        if (ContextCompat.checkSelfPermission(
                app,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            val moshi = Moshi.Builder().build()
            val listType = Types.newParameterizedType(List::class.java, Animal::class.java)
            val adapter: JsonAdapter<List<Animal>> = moshi.adapter(listType)
            val json = adapter.toJson(animalData)
            FileHelper.saveTextToFile(app, json)
        }
    }

    private fun readDataFromCache(): List<Animal> {
        val json = FileHelper.readTextFile(app)
        if (json == null) {
            return emptyList()
        }
        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, Animal::class.java)
        val adapter: JsonAdapter<List<Animal>> = moshi.adapter(listType)
        return adapter.fromJson(json) ?: emptyList()
    }
}