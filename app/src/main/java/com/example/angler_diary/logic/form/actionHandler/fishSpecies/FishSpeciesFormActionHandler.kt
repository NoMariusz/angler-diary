package com.example.angler_diary.logic.form.actionHandler.fishSpecies

import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.FishSpecies
import com.example.angler_diary.logic.form.FormActionResult
import com.example.angler_diary.logic.form.actionHandler.FormActionHandler

abstract class FishSpeciesFormActionHandler(
    viewModel: DatabaseViewModel,
    protected val entity: FishSpecies
) : FormActionHandler(viewModel) {
    override suspend fun validate(): FormActionResult {
        if (entity.name.isEmpty()) {
            return FormActionResult(false, "name cannot be empty")
        }

        if (entity.baseScore < 0) {
            return FormActionResult(false, "Base score cannot be negative")
        }

        if (entity.averageLength <= 0) {
            return FormActionResult(false, "Average length cannot be negative or 0")
        }

        if (entity.averageWeight <= 0) {
            return FormActionResult(false, "Average weight cannot be negative or 0")
        }

        return FormActionResult(true, null)
    }

    override suspend fun saveNewFScore() {}
}