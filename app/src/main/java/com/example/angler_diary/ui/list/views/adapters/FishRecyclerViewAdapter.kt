package com.example.angler_diary.ui.list.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.angler_diary.R
import com.example.angler_diary.database.entities.FishAndSpeciesName

class FishRecyclerViewAdapter(private var fishList: List<FishAndSpeciesName>) :
    FishingObjectListRecyclerViewAdapter<FishAndSpeciesName, FishRecyclerViewAdapter.FishViewHolder>() {

    inner class FishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.textSpeciesName)
        val weightTextView: TextView = itemView.findViewById(R.id.textWeight)
        val lengthTextView: TextView = itemView.findViewById(R.id.textLength)
        val dateTextView: TextView = itemView.findViewById(R.id.textDate)
        val pointsTextView: TextView = itemView.findViewById(R.id.textPoints)
    }

    override fun setObjects(objects: List<FishAndSpeciesName>) {
        this.fishList = objects
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FishViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_fish, parent, false)
        return FishViewHolder(view)
    }

    override fun onBindViewHolder(holder: FishViewHolder, position: Int) {
        val fish = fishList[position]
        holder.nameTextView.text = fish.speciesName
        holder.weightTextView.text = "Weight(g): ${fish.weight ?: "N/A"}"
        holder.lengthTextView.text = "Length(cm): ${fish.length ?: "N/A"}"
        holder.dateTextView.text = "Caught on: ${fish.catchDate}"
        holder.pointsTextView.text = "FScore: ${fish.points ?: "N/A"}"
    }

    override fun getItemCount() = fishList.size
}
