package com.example.angler_diary.ui.home.statisticControllers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.angler_diary.R
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.FishFullData
import com.example.angler_diary.databinding.FragmentHomeBinding
import com.example.angler_diary.ui.FishStatistics
import com.example.angler_diary.ui.UiUtils
import com.example.angler_diary.ui.home.StatisticController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat

class Top3FishesStatisticController(
    private val binding: FragmentHomeBinding,
    private val viewModel: DatabaseViewModel,
    private val context: Context
) : StatisticController {
    private val dateFormat =
        SimpleDateFormat("dd-MM-yyyy HH:mm", context.resources.configuration.locales[0])

    override suspend fun initialize() {
        val top3FishesContainer = binding.top3FishesList

        val top3Fishes = withContext(Dispatchers.IO) {
            viewModel.getTop3Fishes()
        }

        // Clear any existing views
        top3FishesContainer.removeAllViews()

        // Add new views to container
        for (i in top3Fishes.indices) {
            loadFishItem(top3FishesContainer, top3Fishes[i], i + 1)
        }
    }

    private fun loadFishItem(container: LinearLayout, fish: FishFullData, position: Int) {
        val fishView = LayoutInflater.from(context)
            .inflate(R.layout.list_item_fish_top3, container, false) as ConstraintLayout

        // Set place number
        val placeNumberTextView: TextView = fishView.findViewById(R.id.placeNumber)
        placeNumberTextView.text = context.getString(R.string.top3_fishes_item_place, position)

        // Set species name
        val speciesNameTextView: TextView = fishView.findViewById(R.id.textSpeciesName)
        speciesNameTextView.text = fish.speciesName

        // Set points
        val pointsTextView: TextView = fishView.findViewById(R.id.textPoints)
        pointsTextView.text = context.getString(R.string.fScore_score_format, fish.score)

        // Set weight
        val weightTextView: TextView = fishView.findViewById(R.id.textWeight)
        weightTextView.text = UiUtils.getFormattedFishStatisticText(context, fish.weight, FishStatistics.Weight)

        // Set length
        val lengthTextView: TextView = fishView.findViewById(R.id.textLength)
        lengthTextView.text = UiUtils.getFormattedFishStatisticText(context, fish.length, FishStatistics.Length)

        // Format and set catch date
        val dateTextView: TextView = fishView.findViewById(R.id.textDate)
        dateTextView.text =
            context.getString(R.string.top3_fishes_item_date, dateFormat.format(fish.catchDate))

        // Handle ground name
        if (fish.tripGroundName != null) {
            val groundTextView: TextView = fishView.findViewById(R.id.textGround)
            groundTextView.text = context.getString(
                R.string.top3_fishes_item_ground, fish.tripGroundName
            )
        } else {
            // when there is no ground, hide this layout
            val groundLayout: LinearLayout = fishView.findViewById(R.id.layoutGround)
            groundLayout.visibility = View.GONE
        }
        // Add the view to the container
        container.addView(fishView)
    }
}