package com.example.angler_diary.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "score_history")
data class ScoreHistory(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val score: Float,
    val date: Date = Date(),
)
