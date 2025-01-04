package com.example.angler_diary.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.angler_diary.database.entities.Fish
import com.example.angler_diary.database.entities.FishAndSpeciesName

@Dao
interface FishDao {
    @Insert
    suspend fun insert(fish: Fish): Long

    @Query("SELECT * FROM fish")
    fun getAll(): LiveData<List<Fish>>

    @Transaction
    @Query("""
        SELECT *, fish_species.name as speciesName FROM fish 
        inner join fish_species on fish.speciesId = fish_species.id
    """)
    fun getAllWithSpecies(): LiveData<List<FishAndSpeciesName>>
}
