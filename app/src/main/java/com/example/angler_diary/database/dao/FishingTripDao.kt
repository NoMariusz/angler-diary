package com.example.angler_diary.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.angler_diary.database.entities.FishAndSpeciesName
import com.example.angler_diary.database.entities.FishingGround
import com.example.angler_diary.database.entities.FishingTrip
import com.example.angler_diary.database.entities.FishingTripListSummary
import com.example.angler_diary.database.entities.FishingTripSummary

@Dao
interface FishingTripDao {
    @Insert
    suspend fun insert(fishingTrip: FishingTrip): Long

    @Update
    suspend fun update(fishingTrip: FishingTrip)

    @Delete
    suspend fun delete(fishingTrip: FishingTrip)

    @Query("SELECT * FROM fishing_trip where id = :id")
    suspend fun getById(id: Int): FishingTrip?

    @Query("""
        SELECT ft.id, ft.startDate, fishing_ground.name as fishingGroundName FROM fishing_trip ft
        inner join fishing_ground on ft.fishingGroundId = fishing_ground.id
    """)
    suspend fun getAllSummarySuspend(): List<FishingTripSummary>

    @Query("""
        SELECT ft.id, ft.startDate, ft.endDate, ft.score, fishing_ground.name as fishingGroundName FROM fishing_trip ft
        inner join fishing_ground on ft.fishingGroundId = fishing_ground.id
    """)
    fun getTripsSummaryForList(): LiveData<List<FishingTripListSummary>>

    @Query("""
        SELECT f.id, f.image, f.weight, f.length, f.catchDate, f.score, fs.name as speciesName FROM fish f
        inner join fish_species fs on f.speciesId = fs.id
        inner join fishing_trip ft on f.fishingTripId = ft.id
        where fishingTripId = :id
    """)
    suspend fun getTripFishes(id: Int): List<FishAndSpeciesName>

    @Query("SELECT sum(score) from fishing_trip")
    suspend fun getScoreSum(): Float?

}
