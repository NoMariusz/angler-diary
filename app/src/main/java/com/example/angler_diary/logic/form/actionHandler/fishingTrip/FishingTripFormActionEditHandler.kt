package com.example.angler_diary.logic.form.actionHandler.fishingTrip

import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.FishingTrip

class FishingTripFormActionEditHandler(
    viewModel: DatabaseViewModel,
    entity: FishingTrip
) : FishingTripFormActionHandler(viewModel, entity) {
    override suspend fun perform() {
        viewModel.update(entity)
    }
}