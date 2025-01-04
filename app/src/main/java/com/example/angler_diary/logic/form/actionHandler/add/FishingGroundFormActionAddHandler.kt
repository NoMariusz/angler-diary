package com.example.angler_diary.logic.form.actionHandler.add

import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.FishingGround
import com.example.angler_diary.logic.form.FormActionResult

class FishingGroundFormActionAddHandler(
    viewModel: DatabaseViewModel,
    goBackCallback: () -> Unit,
    private val entity: FishingGround
): FormActionAddHandler(viewModel, goBackCallback) {
    override fun validate(): FormActionResult {
        if(entity.name.isEmpty()){
            return FormActionResult(false, "Ground name is empty")
        }

        return FormActionResult(true, null)
    }

    override fun perform(): FormActionResult {
        try {
            viewModel.insertFishingGround(entity)
        } catch (e: Exception) {
            return FormActionResult(false, e.message)
        }

        return FormActionResult(true, null)
    }

    override fun saveNewFScore(): FormActionResult {
        return FormActionResult(true, null)
    }
}