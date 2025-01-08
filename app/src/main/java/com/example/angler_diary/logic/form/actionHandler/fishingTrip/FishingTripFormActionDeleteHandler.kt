package com.example.angler_diary.logic.form.actionHandler.fishingTrip

import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.FishingTrip
import com.example.angler_diary.logic.form.FormActionResult
import com.example.angler_diary.logic.form.score.FScoreController

class FishingTripFormActionDeleteHandler(
    viewModel: DatabaseViewModel,
    entity: FishingTrip
) : FishingTripFormActionHandler(viewModel, entity) {
    override suspend fun perform() {
        viewModel.delete(entity)
    }

    override suspend fun validate(): FormActionResult {
        return FormActionResult(true, null)
    }

    override suspend fun saveNewFScore() {
        FScoreController(viewModel).handleScoreOnDelete()
    }
}