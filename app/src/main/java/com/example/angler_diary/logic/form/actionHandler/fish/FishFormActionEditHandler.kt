package com.example.angler_diary.logic.form.actionHandler.fish

import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.Fish
import com.example.angler_diary.logic.form.score.FScoreController

class FishFormActionEditHandler(
    viewModel: DatabaseViewModel,
    entity: Fish
) : FishFormActionHandler(viewModel, entity) {
    private var fishBeforeUpdate: Fish? = null

    override suspend fun perform() {
        fishBeforeUpdate = viewModel.getFishById(entity.id)
        viewModel.update(entity)
    }

    override suspend fun saveNewFScore() {
        if(fishBeforeUpdate == null) throw Exception("Cannot get fish State before update")
        FScoreController(viewModel).handleScoreOnEntity(entity, fishBeforeUpdate)
    }
}