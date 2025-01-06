package com.example.angler_diary.database.entities

import java.util.Date

data class FishAndSpeciesName(
    val id: Int,
    val image: String?,
    val weight: Float?,
    val length: Float? ,
    val catchDate: Date,
    val points: Float?,
    val speciesName: String
): FishingObjectEntity
