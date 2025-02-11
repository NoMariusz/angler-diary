package com.example.angler_diary.ui.form.manager.adding

import android.content.Context
import android.view.ViewGroup
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.databinding.FragmentFormBinding
import com.example.angler_diary.logic.form.actionHandler.FormActionHandler
import com.example.angler_diary.logic.form.actionHandler.fishingTrip.FishingTripFormActionAddHandler
import com.example.angler_diary.ui.form.manager.common.FishingTripFormManagerCommon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FishingTripAddFormManager(
    viewModel: DatabaseViewModel,
    goBackCallback: () -> Unit,
    context: Context,
    binding: FragmentFormBinding,
) : AddFormManager(viewModel, goBackCallback, context, binding) {
    private val formManagerCommon = FishingTripFormManagerCommon(context)

    override fun createAddActionHandler(): FormActionHandler {
        return FishingTripFormActionAddHandler(
            viewModel, formManagerCommon.getObjectWithActualState()
        )
    }

    override suspend fun performInitialiseInputs(root: ViewGroup) {
        // Switch to IO dispatcher for suspend function
        val grounds = withContext(Dispatchers.IO) {
            viewModel.getAllGrounds()
        }

        // Initialize inputs with the fetched data
        formManagerCommon.initialiseInputs(root, null, grounds)
    }
}