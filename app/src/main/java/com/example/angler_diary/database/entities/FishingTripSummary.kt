package com.example.angler_diary.database.entities

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FishingTripSummary(
    val id: Int,
    val startDate: Date,
    val fishingGroundName: String
): FishingObjectEntity{
    override fun toString(): String {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return "$fishingGroundName - ${dateFormat.format(startDate)}"
    }
}