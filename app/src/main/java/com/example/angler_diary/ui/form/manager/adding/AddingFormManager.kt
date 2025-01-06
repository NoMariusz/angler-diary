package com.example.angler_diary.ui.form.manager.adding

import android.content.Context
import android.view.View
import com.example.angler_diary.R
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.databinding.FragmentFormBinding
import com.example.angler_diary.logic.form.actionHandler.add.FormActionAddHandler
import com.example.angler_diary.ui.form.FormManager


abstract class AddingFormManager(
    protected val viewModel: DatabaseViewModel,
    goBackCallback: () -> Unit,
    context: Context,
    binding: FragmentFormBinding,
) : FormManager(binding, goBackCallback, context) {
    override fun performInitialiseActions(root: View) {
        createAddAction(root)
    }

    abstract fun createAddActionHandler(): FormActionAddHandler

    private fun createAddAction(root: View){
        val btn = root.findViewById<View>(R.id.saveBtn)
        btn.setOnClickListener {
            handleActionClick(createAddActionHandler())
        }
        btn.visibility = View.VISIBLE
    }
}