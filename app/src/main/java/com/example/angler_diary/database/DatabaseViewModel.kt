package com.example.angler_diary.database

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.angler_diary.database.entities.FishingGround
import com.example.angler_diary.database.entities.FishingTrip
import kotlinx.coroutines.launch

class DatabaseViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application, viewModelScope)

    private val fishingGroundDao = db.fishingGroundDao()
    private val fishingTripDao = db.fishingTripDao()
    private val fishSpeciesDao = db.fishSpeciesDao()
    private val fishDao = db.fishDao()

    val allFishSpecies = fishSpeciesDao.getAll()

    val allFishesWithSpecies = fishDao.getAllWithSpecies()

    val allFishingGrounds = fishingGroundDao.getAll()

    val allFishingTripsForList = fishingTripDao.getTripsSummaryForList()

    suspend fun insert(entity: FishingGround) {
        fishingGroundDao.insert(entity)
    }

    suspend fun insert(entity: FishingTrip) {
        fishingTripDao.insert(entity)
    }

    suspend fun ifGroundExists(id: Int): Boolean {
        return fishingGroundDao.getWithId(id) != null
    }

    suspend fun getAllGrounds(): List<FishingGround> {
        return fishingGroundDao.getAllSuspend()
    }
}
