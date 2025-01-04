package com.example.angler_diary.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.angler_diary.database.entities.FishAndSpeciesName
import com.example.angler_diary.database.entities.FishSpecies

class DatabaseViewModel(application: Application): AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application, viewModelScope)

    private val fishingGroundDao = db.fishingGroundDao()
    private val fishingTripDao = db.fishingTripDao()
    private val fishSpeciesDao = db.fishSpeciesDao()
    private val fishDao = db.fishDao()

    val allFishSpecies: LiveData<List<FishSpecies>> = fishSpeciesDao.getAll()
    val allFishesWithSpecies: LiveData<List<FishAndSpeciesName>> = fishDao.getAllWithSpecies()
}
