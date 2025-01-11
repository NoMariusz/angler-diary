package com.example.angler_diary.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.angler_diary.database.entities.ScoreHistory

@Dao
interface ScoreHistoryDao {
    @Insert
    suspend fun insert(entity: ScoreHistory): Long

    // group dates by year, month, day not considering time and get max score from each day
    @Query("""
        SELECT id, STRFTIME('%s', grouped_date) * 1000 as date, max_score as score FROM (
            SELECT 
                id,
                DATE(date / 1000, 'unixepoch') AS grouped_date, 
                MAX(score) AS max_score 
            FROM 
                score_history 
            GROUP BY 
                grouped_date
            ORDER BY 
                grouped_date
        )
    """)
    suspend fun getAllSuspendWithGrouping(): List<ScoreHistory>

    @Query("SELECT * FROM score_history order by date desc limit 1")
    suspend fun getNewest(): ScoreHistory?
}