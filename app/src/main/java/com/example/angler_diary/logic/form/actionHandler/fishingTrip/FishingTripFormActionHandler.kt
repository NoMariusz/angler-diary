package com.example.angler_diary.logic.form.actionHandler.fishingTrip

import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.FishingTrip
import com.example.angler_diary.logic.form.FormActionResult
import com.example.angler_diary.logic.form.actionHandler.FormActionHandler

abstract class FishingTripFormActionHandler(
    viewModel: DatabaseViewModel,
    protected val entity: FishingTrip
) : FormActionHandler(viewModel) {
    override suspend fun validate(): FormActionResult {
        if (!viewModel.ifGroundExists(entity.fishingGroundId)) {
            return FormActionResult(false, "Ground with selected id does not exists")
        }

        if (entity.endDate != null && entity.startDate > entity.endDate) {
            return FormActionResult(false, "Start date should be before end date")
        }

        return FormActionResult(true, null)
    }

    override suspend fun saveNewFScore() {}
}