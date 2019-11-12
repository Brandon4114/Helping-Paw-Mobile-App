package com.example.android.ce301charityapp.Data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.android.ce301charityapp.utilities.FileHelper
import com.example.androiddata.LOG_TAG
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnimalRepository(val app: Application) {

    val animalData = MutableLiveData<List<Animal>>()
    private val animalDao = AnimalDatabase.getDatabase(app).animalDao()
    private val animalImagesDao = AnimalDatabase.getDatabase(app).animalImagesDao()
    private val animalProgressPaointsDao = AnimalDatabase.getDatabase(app).animalProgressPoints()
    private val userDao = AnimalDatabase.getDatabase(app).user()

    private val listType = Types.newParameterizedType(
        List::class.java, Animal::class.java
    )


    init {
        CoroutineScope(Dispatchers.IO).launch {
            val data = animalDao.getAll()
            if (data.isEmpty()) {
//                callWebService()

                val animaldata = FileHelper.getTextFromAssets(app, "animal_data.json" )
                parseJsonAnimalData(animaldata)

                val animalProgressData = FileHelper.getTextFromAssets(app, "animal_progress_data.json")
                parseJsonAnimalProgressData(animalProgressData)

                val animalImagesData = FileHelper.getTextFromAssets(app, "animal_image_data.json")
                parseJsonAnimalImageData(animalImagesData)

                val userData = FileHelper.getTextFromAssets(app, "user_data.json")
                parseJsonUserData(userData)


            } else {
                animalData.postValue(data)
                withContext(Dispatchers.Main) {
                    Toast.makeText(app, "Using local data", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private suspend fun parseJsonAnimalData(text:String){

        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<List<Animal>> = moshi.adapter(listType)

        val animalData : List<Animal>? = adapter.fromJson(text)

        //animalData.postValue(data)
        animalDao.deleteAll()
        if (animalData != null) {
            animalDao.insertAnimals(animalData)
        }

        for (animal in animalData ?: emptyList()){
           Log.i(LOG_TAG, "${animal.animalName} ${animal.animalDescription}")
       }
    }
    private suspend fun parseJsonAnimalImageData(text: String){
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<List<AnimalImages>> = moshi.adapter(listType)

        val animalImageData : List<AnimalImages>? = adapter.fromJson(text)
        animalImagesDao.deleteAll()

        if (animalImageData != null) {
            animalImagesDao.insertAnimalImages(animalImageData)
        }

    }

    private suspend fun parseJsonAnimalProgressData(text: String){
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<List<AnimalProgressPoints>> = moshi.adapter(listType)

        val animalProgressData : List<AnimalProgressPoints>? = adapter.fromJson(text)
        animalProgressPaointsDao.deleteAll()

        if (animalProgressData != null) {
            animalProgressPaointsDao.insertAnimalProgress(animalProgressData)
        }

    }

    private suspend fun parseJsonUserData(text: String){
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<List<User>> = moshi.adapter(listType)

        val userData : List<User>? = adapter.fromJson(text)
        userDao.deleteAll()

        if (userData != null) {
            userDao.insertUsers(userData)
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
            //val retrofit = Retrofit.Builder()
            //   .baseUrl(WEB_SERVICE_URL)
            //  .addConverterFactory(MoshiConverterFactory.create())
            //   .build()
            //val service = retrofit.create(AnimalService::class.java)
            //val serviceData = service.getAnimalData().body() ?: emptyList()
//            animalData.postValue(serviceData)
//            animalDao.deleteAll()
//            animalDao.insertAnimals(serviceData)
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

//    private fun saveDataToCache(animalData: List<Animal>) {
//        if (ContextCompat.checkSelfPermission(
//                app,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE
//            )
//            == PackageManager.PERMISSION_GRANTED
//        ) {
//            val moshi = Moshi.Builder().build()
//            val listType = Types.newParameterizedType(List::class.java, Animal::class.java)
//            val adapter: JsonAdapter<List<Animal>> = moshi.adapter(listType)
//            val json = adapter.toJson(animalData)
//            FileHelper.saveTextToFile(app, json)
//        }
//    }

//    private fun readDataFromCache(): List<Animal> {
//        val json = FileHelper.readTextFile(app)
//        if (json == null) {
//            return emptyList()
//        }
//        val moshi = Moshi.Builder().build()
//        val listType = Types.newParameterizedType(List::class.java, Animal::class.java)
//        val adapter: JsonAdapter<List<Animal>> = moshi.adapter(listType)
//        return adapter.fromJson(json) ?: emptyList()
//    }
}