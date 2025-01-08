package com.example.angler_diary.logic.form.actionHandler.fish

import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.Fish
import com.example.angler_diary.logic.form.score.FScoreController

class FishFormActionAddHandler(
    viewModel: DatabaseViewModel,
    entity: Fish
) : FishFormActionHandler(viewModel, entity) {
    override suspend fun perform() {
        viewModel.insert(entity)
    }

    override suspend fun saveNewFScore() {
        FScoreController(viewModel).handleScoreOnEntity(entity)
    }
}