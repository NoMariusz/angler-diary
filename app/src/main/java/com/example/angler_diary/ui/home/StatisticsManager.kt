package com.example.angler_diary.ui.home

import android.content.Context
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.databinding.FragmentHomeBinding
import com.example.angler_diary.ui.home.statisticControllers.ActualScoreStatisticController
import com.example.angler_diary.ui.home.statisticControllers.ScoreHistoryChartStatisticController
import kotlinx.coroutines.launch

class StatisticsManager(
    private val binding: FragmentHomeBinding,
    private val viewModel: DatabaseViewModel,
    private val context: Context
) {
    fun loadStatistics(lifecycleScope: LifecycleCoroutineScope) {
        val controllers = listOf(
            ActualScoreStatisticController(binding, viewModel, context),
            ScoreHistoryChartStatisticController(binding, viewModel, context),
        )

        // initialize all of controllers
        lifecycleScope.launch {
            controllers.forEach { controller ->
                controller.initialize()
            }
        }
    }
}