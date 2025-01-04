package com.example.angler_diary.ui.form.manager.adding

import android.content.Context
import android.view.ViewGroup
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.logic.form.actionHandler.add.FishingGroundFormActionAddHandler
import com.example.angler_diary.logic.form.actionHandler.add.FormActionAddHandler
import com.example.angler_diary.ui.form.manager.common.FishingGroundFormManagerCommon

class FishingGroundAddFormManager(
    viewModel: DatabaseViewModel,
    goBackCallback: () -> Unit,
    context: Context
) : AddingFormManager(viewModel, goBackCallback, context) {
    private val formManagerCommon = FishingGroundFormManagerCommon(context)

    override fun createAddActionHandler(): FormActionAddHandler {
        return FishingGroundFormActionAddHandler(
            viewModel,
            goBackCallback,
            formManagerCommon.getObjectWithActualState()
        )
    }

    override fun initialiseInputs(root: ViewGroup) {
        formManagerCommon.initialiseInputs(root, null)
    }
}