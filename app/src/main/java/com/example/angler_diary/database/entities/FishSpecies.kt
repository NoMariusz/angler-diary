package com.example.angler_diary.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.angler_diary.DEFAULT_FISH_SPECIES_AVERAGE_LENGTH
import com.example.angler_diary.DEFAULT_FISH_SPECIES_AVERAGE_WEIGHT
import com.example.angler_diary.DEFAULT_FISH_SPECIES_BASE_SCORE

@Entity(
    tableName = "fish_species", indices = [Index(
        value = ["name"],
        unique = true
    )]
)
data class FishSpecies(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val name: String,
    val image: String? = null,
    val baseScore: Int = DEFAULT_FISH_SPECIES_BASE_SCORE,
    val averageWeight: Float = DEFAULT_FISH_SPECIES_AVERAGE_WEIGHT, // Default in g
    val averageLength: Float = DEFAULT_FISH_SPECIES_AVERAGE_LENGTH // Default in cm
) : FishingObjectEntity
