package com.example.angler_diary.ui.form.manager.editing

import android.content.Context
import android.view.ViewGroup
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.databinding.FragmentFormBinding
import com.example.angler_diary.logic.form.actionHandler.FormActionHandler
import com.example.angler_diary.logic.form.actionHandler.fishingGround.FishingGroundFormActionDeleteHandler
import com.example.angler_diary.logic.form.actionHandler.fishingGround.FishingGroundFormActionEditHandler
import com.example.angler_diary.ui.form.manager.common.FishingGroundFormManagerCommon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FishingGroundEditFormManager(
    id: Int,
    viewModel: DatabaseViewModel,
    goBackCallback: () -> Unit,
    context: Context,
    binding: FragmentFormBinding,
) : EditFormManager(id, viewModel, goBackCallback, context, binding) {
    private val formManagerCommon = FishingGroundFormManagerCommon(context)

    override fun createEditActionHandler(): FormActionHandler {
        return FishingGroundFormActionEditHandler(
            viewModel, formManagerCommon.getObjectWithActualState(id)
        )
    }

    override fun createDeleteActionHandler(): FormActionHandler {
        return FishingGroundFormActionDeleteHandler(
            viewModel, formManagerCommon.getObjectWithActualState(id)
        )
    }

    override suspend fun performInitialiseInputs(root: ViewGroup) {
        // Switch to IO dispatcher for suspend function
        val ground = withContext(Dispatchers.IO) {
            viewModel.getFishingGroundById(id)
        }

        // Initialize inputs with the fetched data
        formManagerCommon.initialiseInputs(root, ground)
    }
}