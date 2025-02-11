package com.example.angler_diary.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "fishing_ground", indices = [Index(
        value = ["name"],
        unique = true
    )]
)
data class FishingGround(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val name: String
) : FishingObjectEntity