package com.example.angler_diary.ui.list.views.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class FishingObjectListRecyclerViewViewHolder(
    itemView: View,
    onClick: ((Int) -> Unit)
) :
    RecyclerView.ViewHolder(itemView) {
     var id: Int = 0

    init {
        itemView.setOnClickListener {
            onClick(id)
        }
    }
}