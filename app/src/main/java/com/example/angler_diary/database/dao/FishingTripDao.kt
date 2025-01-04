package com.example.angler_diary.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.angler_diary.database.entities.FishingTrip
import com.example.angler_diary.database.entities.FishingTripListSummary

@Dao
interface FishingTripDao {
    @Insert
    suspend fun insert(fishingTrip: FishingTrip): Long

    @Query("""
        SELECT *, fishing_ground.name as fishingGroundName FROM fishing_trip
        inner join fishing_ground on fishing_trip.fishingGroundId = fishing_ground.id
    """)
    fun getTripsSummaryForList(): LiveData<List<FishingTripListSummary>>
}
