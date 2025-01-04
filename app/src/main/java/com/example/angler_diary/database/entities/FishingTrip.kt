package com.example.angler_diary.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "fishing_trip",
    foreignKeys = [ForeignKey(
        entity = FishingGround::class,
        parentColumns = ["id"],
        childColumns = ["fishingGroundId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class FishingTrip(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val fishingGroundId: Int,
    val startDate: Date = Date(),
    val endDate: Date? = null,
    val duration: Float = 0.0f, // Calculated, not manually editable
    val points: Float? = null // Automatically calculated
): FishingObjectEntity
