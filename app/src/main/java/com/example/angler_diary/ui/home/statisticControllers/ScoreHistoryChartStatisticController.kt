package com.example.angler_diary.ui.home.statisticControllers

import android.content.Context
import com.example.angler_diary.R
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.databinding.FragmentHomeBinding
import com.example.angler_diary.ui.UiUtils.Companion.getColorFromAttr
import com.example.angler_diary.ui.home.StatisticController
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class ScoreHistoryChartStatisticController(
    private val binding: FragmentHomeBinding,
    private val viewModel: DatabaseViewModel,
    private val context: Context
) : StatisticController {
    override suspend fun initialize() {
        val chart = binding.scoreHistoryChart

        // make entries for chart
        val scoreHistoryData = withContext(Dispatchers.IO) {
            viewModel.getAllScoreHistoryForChart()
        }
        val entries = scoreHistoryData.map {
            BarEntry(it.date.time.toFloat(), it.score)
        }

        // make lineData
        val lineDataSet = LineDataSet(entries, context.resources.getString(R.string.home_score_history_title))
        style(lineDataSet)
        val lineData = LineData(lineDataSet)

        // apply data to chart
        style(chart)
        chart.apply {
            data = lineData
            description.isEnabled = false
            animateX(900)
            xAxis.apply {
                valueFormatter = DateFormatter()
            }
            invalidate()
        }
    }

    private fun style(chart: LineChart) {
        chart.apply {
            legend.apply {
                textColor = getDefaultTextColor()
                textSize = getDefaultTextSize()  * 1.2f
            }
            getAxis(AxisDependency.LEFT).apply {
                textColor = getDefaultTextColor()
                textSize = getDefaultTextSize()
            }
            getAxis(AxisDependency.RIGHT).apply {
                textColor = getDefaultTextColor()
                textSize = getDefaultTextSize()
            }
            xAxis.apply {
                textColor = getDefaultTextColor()
                textSize = getDefaultTextSize() * 0.8f
                setLabelCount(4) // reduce labels count because they overlays without this
            }
        }
    }

    private fun style(lineDataSet: LineDataSet) {
        lineDataSet.apply {
            color = context.getColor(R.color.green_500)
            valueTextColor = getDefaultTextColor()
            valueTextSize = getDefaultTextSize() * 0.7f
            setDrawCircles(true)
            circleColors = listOf(context.getColor(R.color.lime_700), context.getColor(R.color.lime_200))
            setDrawCircleHole(false)
            circleRadius = 5f
            lineWidth = 3f
        }

    }

    private fun getDefaultTextColor(): Int {
        return context.getColorFromAttr(android.R.attr.textColorSecondary)
    }

    private fun getDefaultTextSize(): Float {
        return 14f
    }

    class DateFormatter : ValueFormatter() {
        private val dateFormatter = SimpleDateFormat("dd/MM/yy", Locale.getDefault())

        override fun getFormattedValue(value: Float): String {
            return dateFormatter.format(Date(value.toLong()))
        }
    }
}