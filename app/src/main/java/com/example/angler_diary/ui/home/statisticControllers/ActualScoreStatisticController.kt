package com.example.angler_diary.ui.home.statisticControllers

import android.content.Context
import com.example.angler_diary.R
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.databinding.FragmentHomeBinding
import com.example.angler_diary.ui.home.StatisticController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ActualScoreStatisticController(
    private val binding: FragmentHomeBinding,
    private val viewModel: DatabaseViewModel,
    private val context: Context
) : StatisticController {
    override suspend fun initialize() {
        binding.scoreValue.text = context.getString(R.string.loading)

        val actualScoreData = withContext(Dispatchers.IO) {
            viewModel.getNewestScoreHistory()
        }

        binding.scoreValue.text = "%.2f".format(actualScoreData?.score ?: 0f)
    }
}