package com.example.angler_diary.logic.form.actionHandler.add

import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.logic.form.actionHandler.FormActionHandler

abstract class FormActionAddHandler(
    viewModel: DatabaseViewModel,
    goBackCallback: () -> Unit
): FormActionHandler(viewModel, goBackCallback)