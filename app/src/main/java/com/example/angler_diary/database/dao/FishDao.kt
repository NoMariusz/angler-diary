package com.example.angler_diary.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.angler_diary.database.entities.Fish

@Dao
interface FishDao {
    @Insert
    suspend fun insert(fish: Fish): Long

    @Query("""
        SELECT * FROM fish
        WHERE fishingTripId = :fishingTripId
    """)
    suspend fun getFishByFishingTrip(fishingTripId: Int): List<Fish>
}
