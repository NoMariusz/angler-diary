package com.example.angler_diary.ui.form.manager.editing

import android.content.Context
import android.view.ViewGroup
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.databinding.FragmentFormBinding
import com.example.angler_diary.logic.form.actionHandler.FormActionHandler
import com.example.angler_diary.logic.form.actionHandler.fish.FishFormActionDeleteHandler
import com.example.angler_diary.logic.form.actionHandler.fish.FishFormActionEditHandler
import com.example.angler_diary.ui.form.manager.common.FishFormManagerCommon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FishEditFormManager(
    id: Int,
    viewModel: DatabaseViewModel,
    goBackCallback: () -> Unit,
    context: Context,
    binding: FragmentFormBinding,
) : EditFormManager(id, viewModel, goBackCallback, context, binding) {
    private val formManagerCommon = FishFormManagerCommon(context)

    override fun createEditActionHandler(): FormActionHandler {
        return FishFormActionEditHandler(
            viewModel, formManagerCommon.getObjectWithActualState(id)
        )
    }

    override fun createDeleteActionHandler(): FormActionHandler {
        return FishFormActionDeleteHandler(
            viewModel, formManagerCommon.getObjectWithActualState(id)
        )
    }

    override suspend fun performInitialiseInputs(root: ViewGroup) {
        // Switch to IO dispatcher for suspend function
        val trips = withContext(Dispatchers.IO) {
            viewModel.getAllTripsSummary()
        }

        val species = withContext(Dispatchers.IO) {
            viewModel.getAllFishSpecies()
        }

        val fish = withContext(Dispatchers.IO) {
            viewModel.getFishById(id)
        }

        formManagerCommon.initialiseInputs(root, fish, species, trips)
    }
}