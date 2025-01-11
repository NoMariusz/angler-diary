package com.example.angler_diary.database.entities

import java.util.Date

class FishFullData (
        val id: Int,
        val image: String?,
        val weight: Float?,
        val length: Float?,
        val catchDate: Date,
        val score: Float,
        val speciesName: String,
        val tripGroundName: String?,
        val tripStartDate: Date?
    ): FishingObjectEntity