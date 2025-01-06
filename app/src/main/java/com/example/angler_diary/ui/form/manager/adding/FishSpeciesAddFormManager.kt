package com.example.angler_diary.ui.form.manager.adding

import android.content.Context
import android.view.ViewGroup
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.databinding.FragmentFormBinding
import com.example.angler_diary.logic.form.actionHandler.FormActionHandler
import com.example.angler_diary.logic.form.actionHandler.fishSpecies.FishSpeciesFormActionAddHandler
import com.example.angler_diary.ui.form.manager.common.FishSpeciesFormManagerCommon

class FishSpeciesAddFormManager(
    viewModel: DatabaseViewModel,
    goBackCallback: () -> Unit,
    context: Context,
    binding: FragmentFormBinding,
) : AddFormManager(viewModel, goBackCallback, context, binding) {
    private val formManagerCommon = FishSpeciesFormManagerCommon(context)

    override fun createAddActionHandler(): FormActionHandler {
        return FishSpeciesFormActionAddHandler(
            viewModel,
            formManagerCommon.getObjectWithActualState()
        )
    }

    override suspend fun performInitialiseInputs(root: ViewGroup) {
        formManagerCommon.initialiseInputs(root, null)
    }
}