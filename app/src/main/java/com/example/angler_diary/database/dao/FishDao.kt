package com.example.angler_diary.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.angler_diary.database.entities.Fish
import com.example.angler_diary.database.entities.FishAndSpeciesName

@Dao
interface FishDao {
    @Insert
    suspend fun insert(entity: Fish): Long

    @Update
    suspend fun update(entity: Fish)

    @Delete
    suspend fun delete(entity: Fish)

    @Query("SELECT * FROM fish where id = :id")
    suspend fun getById(id: Int): Fish?

    @Query("SELECT * FROM fish")
    fun getAll(): LiveData<List<Fish>>

    @Query(
        """
        SELECT f.id, f.image, f.weight, f.length, f.catchDate, f.score, fs.name as speciesName FROM fish f
        inner join fish_species fs on f.speciesId = fs.id
    """
    )
    fun getAllWithSpecies(): LiveData<List<FishAndSpeciesName>>

    @Query("SELECT * FROM fish WHERE speciesId = :id")
    suspend fun getBySpeciesId(id: Int): List<Fish>

    @Query("SELECT sum(score) FROM fish WHERE fishingTripId is null")
    suspend fun getScoreSumWhenNotTrip(): Float?
}
