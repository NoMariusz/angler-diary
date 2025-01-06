package com.example.angler_diary.logic.form.actionHandler.fishingGround

import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.FishingGround

class FishingGroundFormActionEditHandler(
    viewModel: DatabaseViewModel,
    entity: FishingGround
) : FishingGroundFormActionHandler(viewModel, entity) {
    override suspend fun perform() {
        viewModel.update(entity)
    }
}