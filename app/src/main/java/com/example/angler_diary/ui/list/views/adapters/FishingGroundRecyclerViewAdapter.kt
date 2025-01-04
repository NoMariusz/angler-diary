package com.example.angler_diary.ui.list.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.angler_diary.FishingObjects
import com.example.angler_diary.R
import com.example.angler_diary.database.entities.FishingGround

class FishingGroundRecyclerViewAdapter(
    private var groundList: List<FishingGround>,
    private val itemClickListener: ((Int, FishingObjects) -> Unit)
) : FishingObjectListRecyclerViewAdapter<FishingGround, FishingGroundRecyclerViewAdapter.FishingGroundViewHolder>() {

    inner class FishingGroundViewHolder(itemView: View) : FishingObjectListRecyclerViewViewHolder(
        itemView,
        { id -> itemClickListener(id, FishingObjects.FishingGround) }) {
        val nameTextView: TextView = itemView.findViewById(R.id.textName)
    }

    override fun setObjects(objects: List<FishingGround>) {
        this.groundList = objects
        notifyDataSetChanged()
    }

    override fun setIdForHolder(holder: FishingGroundViewHolder, position: Int) {
        holder.id = groundList[position].id
    }

    override fun bindDataToView(holder: FishingGroundViewHolder, position: Int) {
        val ground = groundList[position]
        holder.nameTextView.text = ground.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FishingGroundViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_fishing_ground, parent, false)
        return FishingGroundViewHolder(view)
    }

    override fun getItemCount() = groundList.size
}
