package com.example.angler_diary.ui.form.manager.factory

import android.content.Context
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.databinding.FragmentFormBinding
import com.example.angler_diary.ui.form.FormManager

interface FormManagerFactoryInterface {
    fun createManager(
        id: Int?,
        viewModel: DatabaseViewModel,
        context: Context,
        binding: FragmentFormBinding,
        goBackCallback: () -> Unit
    ): FormManager
}