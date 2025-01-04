package com.example.angler_diary.ui.form.manager.factory

import android.content.Context
import com.example.angler_diary.FishingObjects
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.ui.form.FormManager
import com.example.angler_diary.ui.form.manager.adding.FishingGroundAddFormManager

class AddingFormManagerFactory: FormManagerFactory {
    override fun createManager(
        id: Int?,
        fishingObjects: FishingObjects,
        viewModel: DatabaseViewModel,
        context: Context,
        goBackCallback: () -> Unit
    ): FormManager {
        return when (fishingObjects) {
            FishingObjects.Fish -> TODO()
            FishingObjects.FishingTrip -> TODO()
            FishingObjects.FishSpecies -> TODO()
            FishingObjects.FishingGround -> FishingGroundAddFormManager(viewModel, goBackCallback, context)
        }
    }
}