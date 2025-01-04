package com.example.angler_diary.ui.list.views

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.FishingGround
import com.example.angler_diary.ui.list.views.adapters.FishingGroundRecyclerViewAdapter
import com.example.angler_diary.ui.list.views.adapters.FishingObjectListRecyclerViewAdapter

class FishingGroundListView(private val viewModel: DatabaseViewModel) :
    FishingObjectListView<FishingGround>(viewModel) {

    override fun createAdapter(): FishingObjectListRecyclerViewAdapter<FishingGround, out RecyclerView.ViewHolder> {
        return FishingGroundRecyclerViewAdapter(
            getObjects().value ?: emptyList()
        )
    }

    override fun getObjects(): LiveData<List<FishingGround>> {
        return viewModel.allFishingGrounds
    }
}
