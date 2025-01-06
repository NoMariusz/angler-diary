package com.example.angler_diary.logic.form.actionHandler.fishSpecies

import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.FishSpecies
import com.example.angler_diary.logic.form.FormActionResult

class FishSpeciesFormActionDeleteHandler(
    viewModel: DatabaseViewModel,
    entity: FishSpecies
) : FishSpeciesFormActionHandler(viewModel, entity) {
    override suspend fun perform() {
        viewModel.delete(entity)
    }

    override suspend fun validate(): FormActionResult {
        return FormActionResult(true, null)
    }
}