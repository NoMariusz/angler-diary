package com.example.angler_diary.ui.list.views

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.FishSpecies
import com.example.angler_diary.ui.list.views.adapters.FishSpeciesRecyclerViewAdapter
import com.example.angler_diary.ui.list.views.adapters.FishingObjectListRecyclerViewAdapter

class FishSpeciesListView(private val viewModel: DatabaseViewModel) :
    FishingObjectListView<FishSpecies>(viewModel) {
    override fun createAdapter(): FishingObjectListRecyclerViewAdapter<FishSpecies, out RecyclerView.ViewHolder> {
        return FishSpeciesRecyclerViewAdapter(
            getObjects().value ?: emptyList()
        )
    }

    override fun getObjects(): LiveData<List<FishSpecies>> {
        return viewModel.allFishSpecies
    }
}