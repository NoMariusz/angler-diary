package com.example.angler_diary.ui.list.views

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.angler_diary.FishingObjects
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.FishAndSpeciesName
import com.example.angler_diary.ui.list.views.adapters.FishRecyclerViewAdapter
import com.example.angler_diary.ui.list.views.adapters.FishingObjectListRecyclerViewAdapter
import com.example.angler_diary.ui.list.views.adapters.FishingObjectListRecyclerViewViewHolder

class FishListView(private val viewModel: DatabaseViewModel) :
    FishingObjectListView<FishAndSpeciesName>(viewModel) {
    override fun createAdapter(
        itemClickListener: (Int, FishingObjects) -> Unit
    ): FishingObjectListRecyclerViewAdapter<FishAndSpeciesName, out FishingObjectListRecyclerViewViewHolder> {
        return FishRecyclerViewAdapter(
            getObjects().value ?: emptyList(),
            itemClickListener
        )
    }

    override fun getObjects(): LiveData<List<FishAndSpeciesName>> {
        return viewModel.allFishesWithSpecies
    }
}
