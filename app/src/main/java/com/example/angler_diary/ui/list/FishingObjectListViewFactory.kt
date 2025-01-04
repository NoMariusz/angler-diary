package com.example.angler_diary.ui.list

import com.example.angler_diary.FishingObjects
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.FishingObjectEntity
import com.example.angler_diary.ui.list.views.FishSpeciesListView

class FishingObjectListViewFactory {
    companion object {
        fun create(
            viewModel: DatabaseViewModel,
            fishingObject: FishingObjects
        ): FishingObjectListView<out FishingObjectEntity> {
            return when (fishingObject) {
                FishingObjects.Fish -> TODO()
                FishingObjects.FishSpecies -> FishSpeciesListView(viewModel)
                FishingObjects.FishingGround -> TODO()
                FishingObjects.FishingTrip -> TODO()
            }
        }
    }
}