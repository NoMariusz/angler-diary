package com.example.angler_diary.ui.list.views.adapters

import androidx.recyclerview.widget.RecyclerView

abstract class FishingObjectListRecyclerViewAdapter<T, VH : FishingObjectListRecyclerViewViewHolder> :
    RecyclerView.Adapter<VH>() {
    abstract fun setObjects(objects: List<T>)

    override fun onBindViewHolder(holder: VH, position: Int) {
        setIdForHolder(holder, position)
        bindDataToView(holder, position)
    }

    // make as abstract to force children to set this id for holder
    abstract fun setIdForHolder(holder: VH, position: Int)
    abstract fun bindDataToView(holder: VH, position: Int)
}
