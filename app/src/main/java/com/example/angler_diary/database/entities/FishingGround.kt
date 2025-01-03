package com.example.angler_diary.database.entities
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fishing_ground")
data class FishingGround(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)