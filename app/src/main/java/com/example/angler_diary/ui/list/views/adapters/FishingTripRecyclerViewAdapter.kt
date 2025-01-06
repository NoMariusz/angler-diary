package com.example.angler_diary.ui.list.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.angler_diary.FishingObjects
import com.example.angler_diary.R
import com.example.angler_diary.database.entities.FishingTripListSummary

class FishingTripRecyclerViewAdapter(
    private var tripList: List<FishingTripListSummary>,
    private val itemClickListener: ((Int, FishingObjects) -> Unit)
) : FishingObjectListRecyclerViewAdapter<FishingTripListSummary, FishingTripRecyclerViewAdapter.FishingTripViewHolder>() {

    inner class FishingTripViewHolder(itemView: View) : FishingObjectListRecyclerViewViewHolder(
        itemView,
        { id -> itemClickListener(id, FishingObjects.FishingTrip) }) {
        val groundNameTextView: TextView = itemView.findViewById(R.id.textGroundName)
        val startDateTextView: TextView = itemView.findViewById(R.id.textStartDate)
        val endDateTextView: TextView = itemView.findViewById(R.id.textEndDate)
        val pointsTextView: TextView = itemView.findViewById(R.id.textPoints)
    }

    override fun setObjects(objects: List<FishingTripListSummary>) {
        this.tripList = objects
        notifyDataSetChanged()
    }

    override fun setIdForHolder(holder: FishingTripViewHolder, position: Int) {
        holder.id = tripList[position].id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FishingTripViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_fishing_trip, parent, false)
        return FishingTripViewHolder(view)
    }

    override fun bindDataToView(holder: FishingTripViewHolder, position: Int) {
        val trip = tripList[position]
        holder.groundNameTextView.text = trip.fishingGroundName
        holder.startDateTextView.text = "Start: ${trip.startDate}"
        holder.endDateTextView.text = trip.endDate?.let { "End: $it" } ?: "Ongoing"
        holder.pointsTextView.text = "FScore: ${trip.score}"
    }

    override fun getItemCount() = tripList.size
}
