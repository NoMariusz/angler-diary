package com.example.angler_diary.ui.form.manager.factory

import android.content.Context
import com.example.angler_diary.FishingObjects
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.ui.form.FormManager

interface FormManagerFactory {
    fun createManager(
        id: Int?,
        fishingObjects: FishingObjects,
        viewModel: DatabaseViewModel,
        context: Context,
        goBackCallback: () -> Unit
    ): FormManager
}