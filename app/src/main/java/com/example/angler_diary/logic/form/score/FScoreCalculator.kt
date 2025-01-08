package com.example.angler_diary.logic.form.score

import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.Fish
import com.example.angler_diary.database.entities.FishingTrip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.sqrt

/**
 * Class for purely calculating entity score
 */
class FScoreCalculator(private val viewModel: DatabaseViewModel) : FScoreVisitor {
    override suspend fun visitFish(fish: Fish): Float {
        val species = withContext(Dispatchers.IO) {
            viewModel.getFishSpeciesById(fish.speciesId)
        }

        if (species == null) throw Exception("Cannot find fish species")

        val weightMultiplier = if (fish.weight != null) fish.weight / species.averageWeight else 1f
        val lengthMultiplier = if (fish.length != null) fish.length / species.averageLength else 1f

        return species.baseScore * sqrt(weightMultiplier * lengthMultiplier)
    }

    override suspend fun visitFishingTrip(fishingTrip: FishingTrip): Float {
        val tripFishes = withContext(Dispatchers.IO) {
            viewModel.getTripFishes(fishingTrip.id)
        }

        // score is sum of fishes score + 1 score per every trip hour
        return tripFishes.sumOf { x -> x.score.toDouble() }.toFloat() + fishingTrip.durationHours * 1f
    }
}