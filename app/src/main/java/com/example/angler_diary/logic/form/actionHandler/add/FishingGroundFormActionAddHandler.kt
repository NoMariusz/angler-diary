package com.example.angler_diary.logic.form.actionHandler.add

import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.FishingGround
import com.example.angler_diary.logic.form.FormActionResult

class FishingGroundFormActionAddHandler(
    viewModel: DatabaseViewModel,
    private val entity: FishingGround
) : FormActionAddHandler(viewModel) {
    override suspend fun validate(): FormActionResult {
        if (entity.name.isEmpty()) {
            return FormActionResult(false, "Ground name is empty")
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