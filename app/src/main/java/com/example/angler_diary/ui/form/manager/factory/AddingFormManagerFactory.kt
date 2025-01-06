package com.example.angler_diary.ui.form.manager.factory

import android.content.Context
import com.example.angler_diary.FishingObjects
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.databinding.FragmentFormBinding
import com.example.angler_diary.ui.form.FormManager
import com.example.angler_diary.ui.form.manager.adding.FishingGroundAddFormManager
import com.example.angler_diary.ui.form.manager.adding.FishingTripAddFormManager

class AddingFormManagerFactory : FormManagerFactory {
    override fun createManager(
        id: Int?,
        fishingObjects: FishingObjects,
        viewModel: DatabaseViewModel,
        context: Context,
        binding: FragmentFormBinding,
        goBackCallback: () -> Unit
    ): FormManager {
        return when (fishingObjects) {
            FishingObjects.Fish -> TODO()
            FishingObjects.FishingTrip -> FishingTripAddFormManager(
                viewModel,
                goBackCallback,
                context,
                binding
            )

            FishingObjects.FishSpecies -> TODO()
            FishingObjects.FishingGround -> FishingGroundAddFormManager(
                viewModel,
                goBackCallback,
                context,
                binding
            )
        }
    }
}