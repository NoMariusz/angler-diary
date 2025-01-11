package com.example.angler_diary.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.angler_diary.database.entities.Fish
import com.example.angler_diary.database.entities.FishAndSpeciesName
import com.example.angler_diary.database.entities.FishFullData

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
        order by f.catchDate desc
    """
    )
    fun getAllWithSpecies(): LiveData<List<FishAndSpeciesName>>

    @Query("SELECT * FROM fish WHERE speciesId = :id")
    suspend fun getBySpeciesId(id: Int): List<Fish>

    @Query("SELECT sum(score) FROM fish WHERE fishingTripId is null")
    suspend fun getScoreSumWhenNotTrip(): Float?

    @Query("""
        SELECT f.id, f.image, f.weight, f.length, f.catchDate, f.score, 
            fs.name as speciesName, fg.name as tripGroundName, ft.startDate as tripStartDate
        from fish f
        inner join fish_species fs on f.speciesId = fs.id
        left join fishing_trip ft on f.fishingTripId = ft.id
        left join fishing_ground fg on ft.fishingGroundId = fg.id
        order by f.score desc limit 3
      """)
    suspend fun getTop3(): List<FishFullData>
}
