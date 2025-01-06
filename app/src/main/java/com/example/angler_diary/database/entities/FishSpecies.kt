package com.example.angler_diary.database.entities
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "fish_species", indices = [Index(
    value = ["name"],
    unique = true
)])
data class FishSpecies(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val image: String? = null,
    val basePoints: Int = 100,
    val averageWeight: Float = 1000.0f, // Default in g
    val averageLength: Float = 30.0f // Default in cm
): FishingObjectEntity
