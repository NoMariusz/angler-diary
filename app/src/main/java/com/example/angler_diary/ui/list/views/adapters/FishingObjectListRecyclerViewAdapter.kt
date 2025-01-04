package com.example.angler_diary.ui.list.views.adapters

import androidx.recyclerview.widget.RecyclerView

abstract class FishingObjectListRecyclerViewAdapter<T, VH: RecyclerView.ViewHolder>: RecyclerView.Adapter<VH>() {
    abstract fun setObjects(objects: List<T>)
}