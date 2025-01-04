package com.example.angler_diary.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope

class DatabaseViewModel(application: Application): AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application, viewModelScope)

    private val fishingGroundDao = db.fishingGroundDao()
    private val fishingTripDao = db.fishingTripDao()
    private val fishSpeciesDao = db.fishSpeciesDao()
    private val fishDao = db.fishDao()

    val allFishSpecies = fishSpeciesDao.getAll()
    val allFishesWithSpecies = fishDao.getAllWithSpecies()
    val allFishingGrounds = fishingGroundDao.getAll()
    val allFishingTripsForList = fishingTripDao.getTripsSummaryForList()
}
