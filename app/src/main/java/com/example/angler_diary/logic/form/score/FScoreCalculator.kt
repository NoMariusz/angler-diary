package com.example.angler_diary.logic.form.score

import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.Fish
import com.example.angler_diary.database.entities.FishingTrip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.pow

/**
 * Class for purely calculating entity score
 */
class FScoreCalculator(private val viewModel: DatabaseViewModel) : FScoreVisitor {
    override suspend fun visitFish(fish: Fish): Float {
        val species = withContext(Dispatchers.IO) {
            viewModel.getFishSpeciesById(fish.speciesId)
        }

        if (species == null) throw Exception("Cannot find fish species")

        val multipliers = mutableListOf<Float>()

        if (fish.weight != null){
            multipliers.add(fish.weight / species.averageWeight)
        }

        if (fish.length != null){
            multipliers.add(fish.length / species.averageLength)
        }

        // multiply base score by multipliers product (sum but for multiplication) normalised by root
        val statisticMultipliersMultiplier = if(multipliers.size >= 1) multipliers.fold(1f){acc, num -> acc * num}.pow(1/multipliers.size) else 1f

        return species.baseScore * statisticMultipliersMultiplier
    }

    override suspend fun visitFishingTrip(fishingTrip: FishingTrip): Float {
        val tripFishes = withContext(Dispatchers.IO) {
            viewModel.getTripFishes(fishingTrip.id)
        }

        // score is sum of fishes score + 1 score per every trip hour
        return tripFishes.sumOf { x -> x.score.toDouble() }.toFloat() + fishingTrip.durationHours * 1f
    }
}