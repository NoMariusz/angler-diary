package com.example.angler_diary.ui.form.manager.editing

import android.content.Context
import android.view.ViewGroup
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.databinding.FragmentFormBinding
import com.example.angler_diary.logic.form.actionHandler.FormActionHandler
import com.example.angler_diary.logic.form.actionHandler.fishingTrip.FishingTripFormActionDeleteHandler
import com.example.angler_diary.logic.form.actionHandler.fishingTrip.FishingTripFormActionEditHandler
import com.example.angler_diary.ui.form.manager.common.FishingTripFormManagerCommon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FishingTripEditFormManager(
    id: Int,
    viewModel: DatabaseViewModel,
    goBackCallback: () -> Unit,
    context: Context,
    binding: FragmentFormBinding,
) : EditFormManager(id, viewModel, goBackCallback, context, binding) {
    private val formManagerCommon = FishingTripFormManagerCommon(context)

    override fun createEditActionHandler(): FormActionHandler {
        return FishingTripFormActionEditHandler(
            viewModel, formManagerCommon.getObjectWithActualState(id)
        )
    }

    override fun createDeleteActionHandler(): FormActionHandler {
        return FishingTripFormActionDeleteHandler(
            viewModel, formManagerCommon.getObjectWithActualState(id)
        )
    }

    override suspend fun performInitialiseInputs(root: ViewGroup) {
        // Switch to IO dispatcher for suspend function
        val grounds = withContext(Dispatchers.IO) {
            viewModel.getAllGrounds()
        }
        val trip = withContext(Dispatchers.IO) {
            viewModel.getById(id)
        }

        // Initialize inputs with the fetched data
        formManagerCommon.initialiseInputs(root, trip, grounds)
    }
}