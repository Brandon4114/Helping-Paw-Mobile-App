package com.example.android.ce301charityapp.ui.shared

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import com.example.android.ce301charityapp.data.Animal
import com.example.android.ce301charityapp.data.AnimalRepository
import com.example.android.ce301charityapp.data.ProgressPoints

class SharedViewModel(val app: Application) : AndroidViewModel(app) {
    private val dataRepo = AnimalRepository(app)
    val animalData = dataRepo.animalData
    val selectedAnimal = MutableLiveData<Animal>()
    val activityTitle = MutableLiveData<String>()

    val progressData = dataRepo.progressData


    init {
        updateActivityTitle()
    }

    fun refreshData() {
        dataRepo.refreshDataFromWeb()
    }


    fun updateActivityTitle(){
        val signature =
            PreferenceManager.getDefaultSharedPreferences(app)
                .getString("signature", "Your animals")
        activityTitle.value = "Animals sponsored by: $signature"
    }

}
