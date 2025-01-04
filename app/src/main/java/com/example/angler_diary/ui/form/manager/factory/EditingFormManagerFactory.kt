package com.example.angler_diary.ui.form.manager.factory

import android.content.Context
import android.content.Intent
import com.example.angler_diary.FishingObjects
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.ui.form.FormManager

class EditingFormManagerFactory: FormManagerFactory {
    override fun createManager(
        id: Int?,
        fishingObjects: FishingObjects,
        viewModel: DatabaseViewModel,
        context: Context,
        goBackCallback: () -> Unit
    ): FormManager {
        TODO("Not yet implemented")
    }
}