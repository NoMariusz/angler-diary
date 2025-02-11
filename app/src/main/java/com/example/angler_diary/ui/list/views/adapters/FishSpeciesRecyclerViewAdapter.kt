package com.example.angler_diary.ui.list.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.angler_diary.FishingObjects
import com.example.angler_diary.R
import com.example.angler_diary.database.entities.FishSpecies

class FishSpeciesRecyclerViewAdapter(
    private var speciesList: List<FishSpecies>,
    private val itemClickListener: ((Int, FishingObjects) -> Unit)
) : FishingObjectListRecyclerViewAdapter<FishSpecies, FishSpeciesRecyclerViewAdapter.FishSpeciesViewHolder>() {

    inner class FishSpeciesViewHolder(itemView: View) : FishingObjectListRecyclerViewViewHolder(
        itemView,
        { id -> itemClickListener(id, FishingObjects.FishSpecies) }) {
        val nameTextView: TextView = itemView.findViewById(R.id.textName)
        val basePointsTextView: TextView = itemView.findViewById(R.id.textBasePoints)
        val averageLengthTextView: TextView = itemView.findViewById(R.id.textAverageLength)
        val averageWeightTextView: TextView = itemView.findViewById(R.id.textAverageWeight)
    }

    override fun setObjects(objects: List<FishSpecies>) {
        this.speciesList = objects
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FishSpeciesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_fish_species, parent, false)
        return FishSpeciesViewHolder(view)
    }

    override fun setIdForHolder(holder: FishSpeciesViewHolder, position: Int) {
        holder.id = speciesList[position].id
    }

    override fun bindDataToView(holder: FishSpeciesViewHolder, position: Int) {
        val species = speciesList[position]
        holder.nameTextView.text = species.name
        holder.basePointsTextView.text = "Base FScore: ${species.baseScore}"
        holder.averageLengthTextView.text = "Average Length(cm): ${species.averageLength}"
        holder.averageWeightTextView.text = "Average Weight(g): ${species.averageWeight}"
    }

    override fun getItemCount() = speciesList.size
}