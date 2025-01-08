package com.example.angler_diary.logic.form.actionHandler.fish

import com.example.angler_diary.EMPTY_ID
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.Fish
import com.example.angler_diary.logic.form.FormActionResult
import com.example.angler_diary.logic.form.actionHandler.FormActionHandler
import com.example.angler_diary.logic.form.score.FScoreController

abstract class FishFormActionHandler(
    viewModel: DatabaseViewModel,
    protected val entity: Fish
) : FormActionHandler(viewModel) {
    override suspend fun validate(): FormActionResult {
        if ((entity.fishingTripId != null && entity.fishingTripId != EMPTY_ID) && !viewModel.ifTripExists(
                entity.fishingTripId
            )
        ) {
            return FormActionResult(false, "Trip with selected id does not exists")
        }

        if (!viewModel.ifFishSpeciesExists(entity.speciesId)) {
            return FormActionResult(false, "Species with selected id does not exists")
        }

        return FormActionResult(true, null)
    }
}