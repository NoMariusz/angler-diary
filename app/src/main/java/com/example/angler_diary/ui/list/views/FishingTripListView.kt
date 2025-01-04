package com.example.angler_diary.ui.list.views

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.FishingTripListSummary
import com.example.angler_diary.ui.list.views.adapters.FishingTripRecyclerViewAdapter
import com.example.angler_diary.ui.list.views.adapters.FishingObjectListRecyclerViewAdapter

class FishingTripListView(private val viewModel: DatabaseViewModel) :
    FishingObjectListView<FishingTripListSummary>(viewModel) {

    override fun createAdapter(): FishingObjectListRecyclerViewAdapter<FishingTripListSummary, out RecyclerView.ViewHolder> {
        return FishingTripRecyclerViewAdapter(
            getObjects().value ?: emptyList()
        )
    }

    override fun getObjects(): LiveData<List<FishingTripListSummary>> {
        return viewModel.allFishingTripsForList
    }
}
