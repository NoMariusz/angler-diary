package com.example.angler_diary.ui.list.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.angler_diary.R
import com.example.angler_diary.database.entities.FishingGround

class FishingGroundRecyclerViewAdapter(private var groundList: List<FishingGround>) :
    FishingObjectListRecyclerViewAdapter<FishingGround, FishingGroundRecyclerViewAdapter.FishingGroundViewHolder>() {

    inner class FishingGroundViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.textName)
    }

    override fun setObjects(objects: List<FishingGround>) {
        this.groundList = objects
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FishingGroundViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_fishing_ground, parent, false)
        return FishingGroundViewHolder(view)
    }

    override fun onBindViewHolder(holder: FishingGroundViewHolder, position: Int) {
        val ground = groundList[position]
        holder.nameTextView.text = ground.name
    }

    override fun getItemCount() = groundList.size
}
