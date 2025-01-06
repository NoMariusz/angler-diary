package com.example.angler_diary.logic.form.actionHandler.fishingGround

import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.FishingGround
import com.example.angler_diary.logic.form.FormActionResult

class FishingGroundFormActionDeleteHandler(
    viewModel: DatabaseViewModel,
    entity: FishingGround
) : FishingGroundFormActionHandler(viewModel, entity) {
    override suspend fun perform() {
        viewModel.delete(entity)
    }

    override suspend fun validate(): FormActionResult {
        return FormActionResult(true, null)
    }
}