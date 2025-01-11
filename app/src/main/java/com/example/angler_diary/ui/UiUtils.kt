package com.example.angler_diary.ui

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.example.angler_diary.R

enum class FishStatistics {
    Weight,
    Length
}


class UiUtils {
    companion object {
        @ColorInt
        fun Context.getColorFromAttr(
            @AttrRes attrColor: Int,
            typedValue: TypedValue = TypedValue(),
            resolveRefs: Boolean = true
        ): Int {
            return if (theme.resolveAttribute(attrColor, typedValue, resolveRefs))
                ContextCompat.getColor(this, typedValue.resourceId)
            else Color.RED
        }

        fun getFormattedFishStatisticText(
            context: Context,
            value: Float?,
            statistic: FishStatistics
        ): String {
            return context.getString(
                getResourceByStatistic(statistic),
                value?.let { "%.2f".format(it) } ?: "?")
        }

        private fun getResourceByStatistic(statistic: FishStatistics): Int {
            return when (statistic) {
                FishStatistics.Length -> R.string.fish_item_length
                FishStatistics.Weight -> R.string.fish_item_weight
            }
        }
    }
}