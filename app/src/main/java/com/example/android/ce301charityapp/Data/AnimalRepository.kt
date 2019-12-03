package com.example.android.ce301charityapp.Data

import android.app.Application
import android.util.Log
import android.widget.Toast
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
    private val animalProgressPointsDao = AnimalDatabase.getDatabase(app).animalProgressPoints()

    private val listType1 = Types.newParameterizedType(
        List::class.java, Animal::class.java
    )
    private val listType2 = Types.newParameterizedType(
        List::class.java, AnimalProgressPoints::class.java
    )

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val data = animalDao.getAll()
            if (data.isEmpty()) {
//                callWebService()

                val animalData = FileHelper.getTextFromAssets(app, "animal_data.json" )
                parseJsonAnimalData(animalData)

                val animalProgressData = FileHelper.getTextFromAssets(app, "animal_progress_data.json")
                parseJsonAnimalProgressData(animalProgressData)

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
        val adapter: JsonAdapter<List<Animal>> = moshi.adapter(listType1)

        val animalData : List<Animal>? = adapter.fromJson(text)

        if (animalData != null) {
            animalDao.insertAnimals(animalData)
        }

        if (animalData != null) {
            for (animal in animalDao.getAll()){
                Log.i(LOG_TAG, "${animal.ID} | ${animal.animalName} | ${animal.animalDescription}")
            }
        }
    }


    private suspend fun parseJsonAnimalProgressData(text: String){
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<List<AnimalProgressPoints>> = moshi.adapter(listType2)

        val animalProgressData : List<AnimalProgressPoints>? = adapter.fromJson(text)
        animalProgressPointsDao.deleteAll()

        if (animalProgressData != null) {
            animalProgressPointsDao.insertAnimalProgress(animalProgressData)
        }
    }

}