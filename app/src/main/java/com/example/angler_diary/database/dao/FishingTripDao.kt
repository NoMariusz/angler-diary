package com.example.angler_diary.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.angler_diary.database.entities.FishingGround
import com.example.angler_diary.database.entities.FishingTrip
import com.example.angler_diary.database.entities.FishingTripListSummary

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
        SELECT ft.id, ft.startDate, ft.endDate, ft.points, fishing_ground.name as fishingGroundName FROM fishing_trip ft
        inner join fishing_ground on ft.fishingGroundId = fishing_ground.id
    """)
    fun getTripsSummaryForList(): LiveData<List<FishingTripListSummary>>
}
