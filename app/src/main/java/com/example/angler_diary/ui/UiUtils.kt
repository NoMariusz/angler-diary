package com.example.angler_diary.ui

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat


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
    }
}