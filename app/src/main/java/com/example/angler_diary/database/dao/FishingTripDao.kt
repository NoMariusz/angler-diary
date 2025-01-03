package com.example.angler_diary.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.angler_diary.database.entities.FishingTrip

@Dao
interface FishingTripDao {
    @Insert
    suspend fun insert(fishingTrip: FishingTrip): Long

    @Query("""
        SELECT * FROM fishing_trip
        WHERE fishingGroundId = :fishingGroundId
    """)
    suspend fun getTripsByFishingGround(fishingGroundId: Int): List<FishingTrip>
}
