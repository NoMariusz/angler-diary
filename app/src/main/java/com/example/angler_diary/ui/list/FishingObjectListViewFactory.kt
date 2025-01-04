package com.example.angler_diary.ui.list

import com.example.angler_diary.FishingObjects
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.FishingObjectEntity
import com.example.angler_diary.ui.list.views.FishListView
import com.example.angler_diary.ui.list.views.FishSpeciesListView
import com.example.angler_diary.ui.list.views.FishingGroundListView
import com.example.angler_diary.ui.list.views.FishingObjectListView

class FishingObjectListViewFactory {
    companion object {
        fun create(
            viewModel: DatabaseViewModel,
            fishingObject: FishingObjects
        ): FishingObjectListView<out FishingObjectEntity> {
            return when (fishingObject) {
                FishingObjects.Fish -> FishListView(viewModel)
                FishingObjects.FishSpecies -> FishSpeciesListView(viewModel)
                FishingObjects.FishingGround -> FishingGroundListView(viewModel)
                FishingObjects.FishingTrip -> TODO()
            }
        }
    }
}