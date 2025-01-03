package com.example.angler_diary.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.angler_diary.database.entities.FishingGround

@Dao
interface FishingGroundDao {
    @Insert
    suspend fun insert(fishingGround: FishingGround): Long

    @Query("SELECT * FROM fishing_ground")
    suspend fun getAll(): List<FishingGround>
}
