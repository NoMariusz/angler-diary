package com.example.angler_diary.ui.form.manager.factory

import android.content.Context
import com.example.angler_diary.FishingObjects
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.databinding.FragmentFormBinding
import com.example.angler_diary.ui.form.FormManager
import com.example.angler_diary.ui.form.manager.editing.FishingGroundEditFormManager
import com.example.angler_diary.ui.form.manager.editing.FishingTripEditFormManager

class EditingFormManagerFactory : FormManagerFactory {
    override fun createManager(
        id: Int?,
        fishingObjects: FishingObjects,
        viewModel: DatabaseViewModel,
        context: Context,
        binding: FragmentFormBinding,
        goBackCallback: () -> Unit
    ): FormManager {
        if (id == null)
            throw Exception("Cannot create EditFormManager without id!")

        return when (fishingObjects) {
            FishingObjects.Fish -> TODO()
            FishingObjects.FishingTrip -> FishingTripEditFormManager(
                id,
                viewModel,
                goBackCallback,
                context,
                binding
            )

            FishingObjects.FishSpecies -> TODO()
            FishingObjects.FishingGround -> FishingGroundEditFormManager(
                id,
                viewModel,
                goBackCallback,
                context,
                binding
            )
        }
    }
}