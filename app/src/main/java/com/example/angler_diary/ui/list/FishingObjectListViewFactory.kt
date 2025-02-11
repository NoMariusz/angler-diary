package com.example.angler_diary.ui.list

import android.content.Context
import com.example.angler_diary.FishingObjects
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.FishingObjectEntity
import com.example.angler_diary.ui.list.views.FishListView
import com.example.angler_diary.ui.list.views.FishSpeciesListView
import com.example.angler_diary.ui.list.views.FishingGroundListView
import com.example.angler_diary.ui.list.views.FishingObjectListView
import com.example.angler_diary.ui.list.views.FishingTripListView

class FishingObjectListViewFactory {
    companion object {
        fun create(
            viewModel: DatabaseViewModel,
            fishingObject: FishingObjects,
            context: Context
        ): FishingObjectListView<out FishingObjectEntity> {
            return when (fishingObject) {
                FishingObjects.Fish -> FishListView(viewModel, context)
                FishingObjects.FishSpecies -> FishSpeciesListView(viewModel)
                FishingObjects.FishingGround -> FishingGroundListView(viewModel)
                FishingObjects.FishingTrip -> FishingTripListView(viewModel)
            }
        }
    }
}