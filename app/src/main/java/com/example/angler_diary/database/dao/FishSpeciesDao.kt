package com.example.angler_diary.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.angler_diary.database.entities.FishSpecies

@Dao
interface FishSpeciesDao {
    @Insert
    suspend fun insert(fishSpecies: FishSpecies): Long

    @Insert
    suspend fun insertAll(fishSpecies: List<FishSpecies>)

    @Query("SELECT * FROM fish_species")
    fun getAll(): LiveData<List<FishSpecies>>
}
