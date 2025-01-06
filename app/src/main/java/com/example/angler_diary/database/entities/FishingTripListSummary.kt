package com.example.angler_diary.database.entities

import java.util.Date

data class FishingTripListSummary(
    val id: Int,
    val fishingGroundName: String,
    val startDate: Date,
    val endDate: Date?,
    val score: Float?
): FishingObjectEntity
