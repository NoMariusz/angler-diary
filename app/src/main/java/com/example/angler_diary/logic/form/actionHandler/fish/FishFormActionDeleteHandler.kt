package com.example.angler_diary.logic.form.actionHandler.fish

import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.Fish
import com.example.angler_diary.logic.form.FormActionResult

class FishFormActionDeleteHandler(
    viewModel: DatabaseViewModel,
    entity: Fish
) : FishFormActionHandler(viewModel, entity) {
    override suspend fun perform() {
        viewModel.delete(entity)
    }

    override suspend fun validate(): FormActionResult {
        return FormActionResult(true, null)
    }
}