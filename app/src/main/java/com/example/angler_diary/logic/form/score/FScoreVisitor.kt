package com.example.angler_diary.logic.form.score

import com.example.angler_diary.database.entities.Fish
import com.example.angler_diary.database.entities.FishingTrip

interface FScoreVisitor {
    suspend fun visitFish(fish: Fish): Float
    suspend fun visitFishingTrip(fishingTrip: FishingTrip): Float
}
