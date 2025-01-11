package com.example.angler_diary.database

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.angler_diary.database.entities.Fish
import com.example.angler_diary.database.entities.FishAndSpeciesName
import com.example.angler_diary.database.entities.FishFullData
import com.example.angler_diary.database.entities.FishSpecies
import com.example.angler_diary.database.entities.FishingGround
import com.example.angler_diary.database.entities.FishingTrip
import com.example.angler_diary.database.entities.FishingTripListSummary
import com.example.angler_diary.database.entities.FishingTripSummary
import com.example.angler_diary.database.entities.ScoreHistory
import kotlinx.coroutines.launch

class DatabaseViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application, viewModelScope)

    private val fishingGroundDao = db.fishingGroundDao()
    private val fishingTripDao = db.fishingTripDao()
    private val fishSpeciesDao = db.fishSpeciesDao()
    private val fishDao = db.fishDao()
    private val scoreHistoryDao = db.scoreHistoryDao()

    val allFishSpecies = fishSpeciesDao.getAll()

    val allFishesWithSpecies = fishDao.getAllWithSpecies()

    val allFishingGrounds = fishingGroundDao.getAll()

    val allFishingTripsForList = fishingTripDao.getTripsSummaryForList()

    // inserts

    suspend fun insert(entity: FishingGround) {
        val idFromInsert = fishingGroundDao.insert(entity)
        entity.id = idFromInsert.toInt()
    }

    suspend fun insert(entity: FishingTrip) {
        val idFromInsert = fishingTripDao.insert(entity)
        entity.id = idFromInsert.toInt()
    }

    suspend fun insert(entity: Fish) {
        val idFromInsert = fishDao.insert(entity)
        entity.id = idFromInsert.toInt()
    }

    suspend fun insert(entity: FishSpecies) {
        val idFromInsert = fishSpeciesDao.insert(entity)
        entity.id = idFromInsert.toInt()
    }

    suspend fun insert(entity: ScoreHistory) {
        scoreHistoryDao.insert(entity)
    }

    // updates

    suspend fun update(entity: FishingTrip) {
        fishingTripDao.update(entity)
    }

    suspend fun update(entity: FishingGround) {
        fishingGroundDao.update(entity)
    }

    suspend fun update(entity: Fish) {
        fishDao.update(entity)
    }

    suspend fun update(entity: FishSpecies) {
        fishSpeciesDao.update(entity)
    }

    // deletes

    suspend fun delete(entity: FishingTrip) {
        fishingTripDao.delete(entity)
    }

    suspend fun delete(entity: FishingGround) {
        fishingGroundDao.delete(entity)
    }

    suspend fun delete(entity: Fish) {
        fishDao.delete(entity)
    }

    suspend fun delete(entity: FishSpecies) {
        fishSpeciesDao.delete(entity)
    }

    // selects

    suspend fun getFishingTripById(id: Int): FishingTrip? {
        return fishingTripDao.getById(id)
    }

    suspend fun getFishingGroundById(id: Int): FishingGround? {
        return fishingGroundDao.getById(id)
    }

    suspend fun getFishById(id: Int): Fish? {
        return fishDao.getById(id)
    }

    suspend fun getFishSpeciesById(id: Int): FishSpecies? {
        return fishSpeciesDao.getById(id)
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

    suspend fun getTripFishes(id: Int): List<FishAndSpeciesName> {
        return fishingTripDao.getTripFishes(id)
    }

    suspend fun getAllFishSpecies(): List<FishSpecies> {
        return fishSpeciesDao.getAllSuspend()
    }

    suspend fun getFishesBySpeciesId(id: Int): List<Fish> {
        return fishDao.getBySpeciesId(id)
    }

    suspend fun getTop3Fishes(): List<FishFullData> {
        return fishDao.getTop3()
    }

    // score/score history related stuff

    suspend fun getAllScoreHistoryForChart(): List<ScoreHistory> {
        return scoreHistoryDao.getAllSuspendWithGrouping()
    }

    suspend fun getNewestScoreHistory(): ScoreHistory? {
        return scoreHistoryDao.getNewest()
    }

    suspend fun getTripsScoreSum(): Float? {
        return fishingTripDao.getScoreSum()
    }

    suspend fun getFishesWithoutTripScoreSum(): Float? {
        return fishDao.getScoreSumWhenNotTrip()
    }
}
