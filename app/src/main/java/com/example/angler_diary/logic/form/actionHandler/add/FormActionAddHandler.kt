package com.example.angler_diary.logic.form.actionHandler.add

import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.database.entities.FishingObjectEntity
import com.example.angler_diary.logic.form.FormActionResult
import com.example.angler_diary.logic.form.actionHandler.FormActionHandler

abstract class FormActionAddHandler(
    viewModel: DatabaseViewModel
): FormActionHandler(viewModel){
    abstract suspend fun performInsert()

    override suspend fun perform(): FormActionResult {
        try {
            performInsert()
        } catch (e: Exception) {
            return FormActionResult(false, e.message)
        }

        return FormActionResult(true, null)
    }
}