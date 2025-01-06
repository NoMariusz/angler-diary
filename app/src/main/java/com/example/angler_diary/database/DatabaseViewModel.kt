package com.example.angler_diary.database

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.angler_diary.database.entities.Fish
import com.example.angler_diary.database.entities.FishSpecies
import com.example.angler_diary.database.entities.FishingGround
import com.example.angler_diary.database.entities.FishingTrip
import com.example.angler_diary.database.entities.FishingTripListSummary
import com.example.angler_diary.database.entities.FishingTripSummary
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

    suspend fun insert(entity: Fish) {
        fishDao.insert(entity)
    }

    suspend fun update(entity: FishingTrip) {
        fishingTripDao.update(entity)
    }

    suspend fun update(entity: FishingGround) {
        fishingGroundDao.update(entity)
    }

    suspend fun update(entity: Fish) {
        fishDao.update(entity)
    }

    suspend fun delete(entity: FishingTrip) {
        fishingTripDao.delete(entity)
    }

    suspend fun delete(entity: FishingGround) {
        fishingGroundDao.delete(entity)
    }

    suspend fun delete(entity: Fish) {
        fishDao.delete(entity)
    }

    suspend fun getFishingTripById(id: Int): FishingTrip? {
        return fishingTripDao.getById(id)
    }

    suspend fun getFishingGroundById(id: Int): FishingGround? {
        return fishingGroundDao.getById(id)
    }

    suspend fun getFishById(id: Int): Fish? {
        return fishDao.getById(id)
    }

    suspend fun ifGroundExists(id: Int): Boolean {
        return fishingGroundDao.getById(id) != null
    }

    suspend fun ifTripExists(id: Int): Boolean {
        return fishingTripDao.getById(id) != null
    }

    suspend fun ifFishSpeciesExists(id: Int): Boolean {
        return fishSpeciesDao.getById(id) != null
    }

    suspend fun getAllGrounds(): List<FishingGround> {
        return fishingGroundDao.getAllSuspend()
    }

    suspend fun getAllTripsSummary(): List<FishingTripSummary> {
        return fishingTripDao.getAllSummarySuspend()
    }

    suspend fun getAllFishSpecies(): List<FishSpecies> {
        return fishSpeciesDao.getAllSuspend()
    }
}
