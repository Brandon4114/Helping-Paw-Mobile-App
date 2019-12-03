package com.example.android.ce301charityapp.ui.shared

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import com.example.android.ce301charityapp.Data.Animal
import com.example.android.ce301charityapp.Data.AnimalRepository

class SharedViewModel(val app: Application) : AndroidViewModel(app) {
    private val dataRepo = AnimalRepository(app)
    val animalData = dataRepo.animalData
    val selectedAnimal = MutableLiveData<Animal>()
    val activityTitle = MutableLiveData<String>()

    init {
        updateActivityTitle()
    }

    fun updateActivityTitle(){
        val signature =
            PreferenceManager.getDefaultSharedPreferences(app)
                .getString("signature", "Your animals")
        activityTitle.value = "Animals sponsored by: $signature"
    }

}
