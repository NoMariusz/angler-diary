package com.example.angler_diary.logic.form.actionHandler

import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.logic.form.FormActionResult

abstract class FormActionHandler(
    protected val viewModel: DatabaseViewModel,
    private val goBackCallback: () -> Unit
) {
    fun execute(): FormActionResult {
        val validationResult = validate()
        if (!validationResult.success) return validationResult

        val performResult = perform()
        if (!performResult.success) return performResult

        val saveNewFScoreResult = saveNewFScore()
        if (!saveNewFScoreResult.success) return saveNewFScoreResult

        goBackCallback()
        return FormActionResult(true, null)
    }

    abstract fun validate(): FormActionResult
    abstract fun perform(): FormActionResult
    abstract fun saveNewFScore(): FormActionResult
}
