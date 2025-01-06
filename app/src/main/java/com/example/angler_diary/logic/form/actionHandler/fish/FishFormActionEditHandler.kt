package com.example.angler_diary.logic.form.actionHandler.fish

import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.Fish

class FishFormActionEditHandler(
    viewModel: DatabaseViewModel,
    entity: Fish
) : FishFormActionHandler(viewModel, entity) {
    override suspend fun perform() {
        viewModel.update(entity)
    }
}