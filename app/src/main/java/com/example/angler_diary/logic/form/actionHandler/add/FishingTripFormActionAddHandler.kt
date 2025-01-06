package com.example.angler_diary.logic.form.actionHandler.add

import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.FishingTrip
import com.example.angler_diary.logic.form.FormActionResult

class FishingTripFormActionAddHandler(
    viewModel: DatabaseViewModel,
    private val entity: FishingTrip
) : FormActionAddHandler(viewModel) {
    override suspend fun validate(): FormActionResult {
        if (!viewModel.ifGroundExists(entity.fishingGroundId)) {
            return FormActionResult(false, "Ground with selected id does not exists")
        }

        if (entity.endDate != null && entity.startDate > entity.endDate) {
            return FormActionResult(false, "Start date should be before end date")
        }

        return FormActionResult(true, null)
    }

    override suspend fun performInsert() {
        viewModel.insert(entity)
    }

    override suspend fun saveNewFScore(): FormActionResult {
        return FormActionResult(true, null)
    }
}