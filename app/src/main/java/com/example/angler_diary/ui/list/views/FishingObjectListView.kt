package com.example.angler_diary.ui.list.views

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.angler_diary.FishingObjects
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.FishingObjectEntity
import com.example.angler_diary.ui.list.views.adapters.FishingObjectListRecyclerViewAdapter
import com.example.angler_diary.ui.list.views.adapters.FishingObjectListRecyclerViewViewHolder

abstract class FishingObjectListView<T : FishingObjectEntity>(viewModel: DatabaseViewModel) {
    fun prepareRecyclerView(
        recyclerView: RecyclerView,
        context: Context,
        lifecycleOwner: LifecycleOwner,
        itemClickListener: ((Int, FishingObjects) -> Unit)
    ) {
        val adapter = createAdapter(itemClickListener)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        getObjects().observe(lifecycleOwner) { objects ->
            adapter.setObjects(objects)
        }
    }

    abstract fun createAdapter(
        itemClickListener: ((Int, FishingObjects) -> Unit)
    ): FishingObjectListRecyclerViewAdapter<T, out FishingObjectListRecyclerViewViewHolder>

    abstract fun getObjects(): LiveData<List<T>>
}
