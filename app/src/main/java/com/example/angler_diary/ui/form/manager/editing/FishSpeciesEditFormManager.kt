package com.example.angler_diary.ui.form.manager.editing

import android.content.Context
import android.view.ViewGroup
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.databinding.FragmentFormBinding
import com.example.angler_diary.logic.form.actionHandler.FormActionHandler
import com.example.angler_diary.logic.form.actionHandler.fishSpecies.FishSpeciesFormActionDeleteHandler
import com.example.angler_diary.logic.form.actionHandler.fishSpecies.FishSpeciesFormActionEditHandler
import com.example.angler_diary.ui.form.manager.common.FishSpeciesFormManagerCommon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FishSpeciesEditFormManager(
    id: Int,
    viewModel: DatabaseViewModel,
    goBackCallback: () -> Unit,
    context: Context,
    binding: FragmentFormBinding,
) : EditFormManager(id, viewModel, goBackCallback, context, binding) {
    private val formManagerCommon = FishSpeciesFormManagerCommon(context)

    override fun createEditActionHandler(): FormActionHandler {
        return FishSpeciesFormActionEditHandler(
            viewModel, formManagerCommon.getObjectWithActualState(id)
        )
    }

    override fun createDeleteActionHandler(): FormActionHandler {
        return FishSpeciesFormActionDeleteHandler(
            viewModel, formManagerCommon.getObjectWithActualState(id)
        )
    }

    override suspend fun performInitialiseInputs(root: ViewGroup) {
        val species = withContext(Dispatchers.IO) {
            viewModel.getFishSpeciesById(id)
        }

        formManagerCommon.initialiseInputs(root, species)
    }
}