package com.example.angler_diary.ui.list.views.adapters

import android.view.View
import androidx.constraintlayout.motion.widget.MotionScene.Transition.TransitionOnClick
import androidx.recyclerview.widget.RecyclerView
import com.example.angler_diary.FishingObjects
import com.example.angler_diary.FormActivity
import com.example.angler_diary.logic.FormModes

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