package com.example.angler_diary.logic.form.actionHandler.fishSpecies

import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.FishSpecies

class FishSpeciesFormActionEditHandler(
    viewModel: DatabaseViewModel,
    entity: FishSpecies
) : FishSpeciesFormActionHandler(viewModel, entity) {
    override suspend fun perform() {
        viewModel.update(entity)
    }
}