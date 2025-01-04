package com.example.angler_diary.ui.list.views

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.angler_diary.FishingObjects
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.FishSpecies
import com.example.angler_diary.ui.list.views.adapters.FishSpeciesRecyclerViewAdapter
import com.example.angler_diary.ui.list.views.adapters.FishingObjectListRecyclerViewAdapter
import com.example.angler_diary.ui.list.views.adapters.FishingObjectListRecyclerViewViewHolder

class FishSpeciesListView(private val viewModel: DatabaseViewModel) :
    FishingObjectListView<FishSpecies>(viewModel) {
    override fun createAdapter(itemClickListener: (Int, FishingObjects) -> Unit): FishingObjectListRecyclerViewAdapter<FishSpecies, out FishingObjectListRecyclerViewViewHolder> {
        return FishSpeciesRecyclerViewAdapter(
            getObjects().value ?: emptyList(),
            itemClickListener
        )
    }
    override fun getObjects(): LiveData<List<FishSpecies>> {
        return viewModel.allFishSpecies
    }
}