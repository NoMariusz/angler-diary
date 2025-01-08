package com.example.angler_diary.logic.form.score

import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.Fish
import com.example.angler_diary.database.entities.FishSpecies
import com.example.angler_diary.database.entities.FishingTrip

/**
 * Class handling score updates at certain events, considering all consequences resulting from db relations
 * methods handleScoreOnEntity handle score updates on entity add or edit
 * methods handleScoreOnDelete handle score updates on entity delete
 */
class FScoreController(private val viewModel: DatabaseViewModel) {
    private val calculator = FScoreCalculator(viewModel)

    // handling on delete

    suspend fun handleScoreOnDelete() {
        reCalcAllScore()
    }

    suspend fun handleScoreOnDelete(entity: Fish) {
        // update score of related trip
        val relatedTrip =
            entity.fishingTripId?.let { viewModel.getFishingTripById(it) }
        if (relatedTrip != null) updateScore(relatedTrip)

        reCalcAllScore()
    }

    // handling on add/edit

    suspend fun handleScoreOnEntity(entity: Fish, entityBeforeUpdate: Fish? = null) {
        updateScoreAndRelatedObjects(entity, entityBeforeUpdate)

        reCalcAllScore()
    }

    suspend fun handleScoreOnEntity(entity: FishingTrip) {
        updateScore(entity)

        reCalcAllScore()
    }

    suspend fun handleScoreOnEntity(entity: FishSpecies) {
        // recalculate score of related fishes
        val relatedFishes = viewModel.getFishesBySpeciesId(entity.id)
        relatedFishes.forEach { fish ->
            updateScoreAndRelatedObjects(fish)
        }

        reCalcAllScore()
    }

    // updating score and performing all relation consequences

    private suspend fun updateScoreAndRelatedObjects(
        entity: Fish,
        entityBeforeUpdate: Fish? = null
    ) {
        // update fish score
        updateScore(entity)
        // when user disconnected fish from trip, by changing trip, or clearing this field value,
        // update previously related trip score
        if (entityBeforeUpdate?.fishingTripId != null && entity.fishingTripId != entityBeforeUpdate.fishingTripId) {
            val previouslyRelatedTrip =
                viewModel.getFishingTripById(entityBeforeUpdate.fishingTripId)
            if (previouslyRelatedTrip != null) updateScore(previouslyRelatedTrip)
        }

        // update score of related trip
        val relatedTrip =
            entity.fishingTripId?.let { viewModel.getFishingTripById(it) }
        if (relatedTrip != null) updateScore(relatedTrip)
    }

    // purely updating entity score

    private suspend fun updateScore(entity: Fish) {
        val score = calculator.visitFish(entity)
        entity.score = score
        viewModel.update(entity)
    }

    private suspend fun updateScore(entity: FishingTrip) {
        val score = calculator.visitFishingTrip(entity)
        entity.score = score
        viewModel.update(entity)
    }

    // calculating overall score and saving in score history

    private suspend fun reCalcAllScore() {}
}