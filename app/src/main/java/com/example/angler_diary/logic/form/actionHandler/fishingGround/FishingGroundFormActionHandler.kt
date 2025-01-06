package com.example.angler_diary.logic.form.actionHandler.fishingGround

import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.FishingGround
import com.example.angler_diary.logic.form.FormActionResult
import com.example.angler_diary.logic.form.actionHandler.FormActionHandler

abstract class FishingGroundFormActionHandler(
    viewModel: DatabaseViewModel,
    protected val entity: FishingGround
) : FormActionHandler(viewModel) {
    override suspend fun validate(): FormActionResult {
        if (entity.name.isEmpty()) {
            return FormActionResult(false, "Ground name is empty")
        }

        return FormActionResult(true, null)
    }

    override suspend fun saveNewFScore(): FormActionResult {
        return FormActionResult(true, null)
    }
}