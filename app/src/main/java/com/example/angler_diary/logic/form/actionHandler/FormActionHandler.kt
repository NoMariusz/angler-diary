package com.example.angler_diary.logic.form.actionHandler

import android.database.sqlite.SQLiteConstraintException
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.logic.form.FormActionResult

abstract class FormActionHandler(
    protected val viewModel: DatabaseViewModel
) {
    suspend fun execute(): FormActionResult {
        val validationResult = validate()
        if (!validationResult.success) return validationResult

        try {
            perform()
            saveNewFScore()
        } catch (e: SQLiteConstraintException) {
            return FormActionResult(
                false,
                "Cannot delete this object unless other object have relation to him"
            )
        } catch (e: Exception) {
            return FormActionResult(false, e.message)
        }

        return FormActionResult(true, null)
    }

    abstract suspend fun validate(): FormActionResult
    abstract suspend fun perform()
    abstract suspend fun saveNewFScore()
}
