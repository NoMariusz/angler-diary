package com.example.angler_diary.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.angler_diary.database.entities.FishSpecies
import com.example.angler_diary.database.entities.FishingTrip

@Dao
interface FishSpeciesDao {
    @Insert
    suspend fun insertAll(fishSpecies: List<FishSpecies>)

    @Insert
    suspend fun insert(fishSpecies: FishSpecies): Long

    @Query("SELECT * FROM fish_species where id = :id")
    suspend fun getById(id: Int): FishSpecies?

    @Query("SELECT * FROM fish_species")
    fun getAll(): LiveData<List<FishSpecies>>

    @Query("SELECT * FROM fish_species")
    suspend fun getAllSuspend(): List<FishSpecies>
}
