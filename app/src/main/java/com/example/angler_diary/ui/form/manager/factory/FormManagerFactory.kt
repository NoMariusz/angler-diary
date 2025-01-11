package com.example.angler_diary.ui.form.manager.factory

import android.content.Context
import com.example.angler_diary.FishingObjects
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.databinding.FragmentFormBinding
import com.example.angler_diary.logic.form.FormModes
import com.example.angler_diary.ui.form.FormManager
import com.example.angler_diary.ui.form.manager.adding.FishAddFormManager
import com.example.angler_diary.ui.form.manager.adding.FishSpeciesAddFormManager
import com.example.angler_diary.ui.form.manager.adding.FishingGroundAddFormManager
import com.example.angler_diary.ui.form.manager.adding.FishingTripAddFormManager
import com.example.angler_diary.ui.form.manager.editing.FishEditFormManager
import com.example.angler_diary.ui.form.manager.editing.FishSpeciesEditFormManager
import com.example.angler_diary.ui.form.manager.editing.FishingGroundEditFormManager
import com.example.angler_diary.ui.form.manager.editing.FishingTripEditFormManager

class FormManagerFactory(
    private val fishingObjects: FishingObjects, private val mode: FormModes
) : FormManagerFactoryInterface {
    override fun createManager(
        id: Int?,
        viewModel: DatabaseViewModel,
        context: Context,
        binding: FragmentFormBinding,
        goBackCallback: () -> Unit
    ): FormManager {
        return when (mode) {
            FormModes.Adding -> createAddManager(
                fishingObjects, viewModel, context, binding, goBackCallback
            )

            FormModes.Editing -> createEditManager(
                id, fishingObjects, viewModel, context, binding, goBackCallback
            )
        }
    }

    private fun createEditManager(
        id: Int?,
        fishingObjects: FishingObjects,
        viewModel: DatabaseViewModel,
        context: Context,
        binding: FragmentFormBinding,
        goBackCallback: () -> Unit
    ): FormManager {
        if (id == null) throw Exception("Cannot create EditFormManager without id!")

        return when (fishingObjects) {
            FishingObjects.Fish -> FishEditFormManager(
                id, viewModel, goBackCallback, context, binding
            )

            FishingObjects.FishingTrip -> FishingTripEditFormManager(
                id, viewModel, goBackCallback, context, binding
            )

            FishingObjects.FishSpecies -> FishSpeciesEditFormManager(
                id, viewModel, goBackCallback, context, binding
            )

            FishingObjects.FishingGround -> FishingGroundEditFormManager(
                id, viewModel, goBackCallback, context, binding
            )
        }
    }

    private fun createAddManager(
        fishingObjects: FishingObjects,
        viewModel: DatabaseViewModel,
        context: Context,
        binding: FragmentFormBinding,
        goBackCallback: () -> Unit
    ): FormManager {
        return when (fishingObjects) {
            FishingObjects.Fish -> FishAddFormManager(
                viewModel, goBackCallback, context, binding
            )

            FishingObjects.FishingTrip -> FishingTripAddFormManager(
                viewModel, goBackCallback, context, binding
            )

            FishingObjects.FishSpecies -> FishSpeciesAddFormManager(
                viewModel, goBackCallback, context, binding
            )

            FishingObjects.FishingGround -> FishingGroundAddFormManager(
                viewModel, goBackCallback, context, binding
            )
        }
    }
}