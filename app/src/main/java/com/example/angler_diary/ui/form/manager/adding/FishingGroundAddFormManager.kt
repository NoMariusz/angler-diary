package com.example.angler_diary.ui.form.manager.adding

import android.content.Context
import android.view.ViewGroup
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.databinding.FragmentFormBinding
import com.example.angler_diary.logic.form.actionHandler.FormActionHandler
import com.example.angler_diary.logic.form.actionHandler.fishingGround.FishingGroundFormActionAddHandler
import com.example.angler_diary.ui.form.manager.common.FishingGroundFormManagerCommon

class FishingGroundAddFormManager(
    viewModel: DatabaseViewModel,
    goBackCallback: () -> Unit,
    context: Context,
    binding: FragmentFormBinding,
) : AddFormManager(viewModel, goBackCallback, context, binding) {
    private val formManagerCommon = FishingGroundFormManagerCommon(context)

    override fun createAddActionHandler(): FormActionHandler {
        return FishingGroundFormActionAddHandler(
            viewModel,
            formManagerCommon.getObjectWithActualState()
        )
    }

    override suspend fun performInitialiseInputs(root: ViewGroup) {
        formManagerCommon.initialiseInputs(root, null)
    }
}