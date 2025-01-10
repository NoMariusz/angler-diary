package com.example.angler_diary.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.angler_diary.database.entities.ScoreHistory

@Dao
interface ScoreHistoryDao {
    @Insert
    suspend fun insert(entity: ScoreHistory): Long

    @Query("SELECT * FROM score_history")
    suspend fun getAllSuspend(): List<ScoreHistory>

    @Query("SELECT * FROM score_history order by date desc limit 1")
    suspend fun getNewest(): ScoreHistory?
}