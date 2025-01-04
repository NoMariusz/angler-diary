package com.example.angler_diary.ui.list.views

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.angler_diary.FishingObjects
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.FishingGround
import com.example.angler_diary.ui.list.views.adapters.FishingGroundRecyclerViewAdapter
import com.example.angler_diary.ui.list.views.adapters.FishingObjectListRecyclerViewAdapter
import com.example.angler_diary.ui.list.views.adapters.FishingObjectListRecyclerViewViewHolder

class FishingGroundListView(private val viewModel: DatabaseViewModel) :
    FishingObjectListView<FishingGround>(viewModel) {

    override fun createAdapter(
        itemClickListener: (Int, FishingObjects) -> Unit
    ): FishingObjectListRecyclerViewAdapter<FishingGround, out FishingObjectListRecyclerViewViewHolder> {
        return FishingGroundRecyclerViewAdapter(
            getObjects().value ?: emptyList(),
            itemClickListener
        )
    }

    override fun getObjects(): LiveData<List<FishingGround>> {
        return viewModel.allFishingGrounds
    }
}
