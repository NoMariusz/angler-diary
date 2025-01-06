package com.example.angler_diary.logic.form.actionHandler

import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.logic.form.FormActionResult

abstract class FormActionHandler(
    protected val viewModel: DatabaseViewModel
) {
    suspend fun execute(): FormActionResult {
        val validationResult = validate()
        if (!validationResult.success) return validationResult

        val performResult = perform()
        if (!performResult.success) return performResult

        val saveNewFScoreResult = saveNewFScore()
        if (!saveNewFScoreResult.success) return saveNewFScoreResult

        return FormActionResult(true, null)
    }

    abstract suspend fun validate(): FormActionResult
    abstract suspend fun perform(): FormActionResult
    abstract suspend fun saveNewFScore(): FormActionResult
}
