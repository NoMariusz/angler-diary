package com.example.angler_diary.ui.form.manager.adding

import android.content.Context
import android.view.ViewGroup
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.databinding.FragmentFormBinding
import com.example.angler_diary.logic.form.actionHandler.FormActionHandler
import com.example.angler_diary.logic.form.actionHandler.fish.FishFormActionAddHandler
import com.example.angler_diary.ui.form.manager.common.FishFormManagerCommon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FishAddFormManager(
    viewModel: DatabaseViewModel,
    goBackCallback: () -> Unit,
    context: Context,
    binding: FragmentFormBinding,
) : AddFormManager(viewModel, goBackCallback, context, binding) {
    private val formManagerCommon = FishFormManagerCommon(context)

    override fun createAddActionHandler(): FormActionHandler {
        return FishFormActionAddHandler(
            viewModel,
            formManagerCommon.getObjectWithActualState()
        )
    }

    override suspend fun performInitialiseInputs(root: ViewGroup) {
        // Switch to IO dispatcher for suspend function
        val trips = withContext(Dispatchers.IO) {
            viewModel.getAllTripsSummary()
        }

        // Switch to IO dispatcher for suspend function
        val species = withContext(Dispatchers.IO) {
            viewModel.getAllFishSpecies()
        }

        formManagerCommon.initialiseInputs(root, null, species, trips)
    }
}