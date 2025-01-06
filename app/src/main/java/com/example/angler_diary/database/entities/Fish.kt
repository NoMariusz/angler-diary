package com.example.angler_diary.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "fish",
    foreignKeys = [
        ForeignKey(
            entity = FishSpecies::class,
            parentColumns = ["id"],
            childColumns = ["speciesId"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = FishingTrip::class,
            parentColumns = ["id"],
            childColumns = ["fishingTripId"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class Fish(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val speciesId: Int,
    val image: String? = null,
    val weight: Float? = null, // In g
    val length: Float? = null, // In cm
    val catchDate: Date = Date(),
    val fishingTripId: Int? = null,
    val points: Float? = null // Automatically calculated
): FishingObjectEntity
