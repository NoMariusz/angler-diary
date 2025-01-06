package com.example.angler_diary.ui.form.manager.editing

import android.content.Context
import android.view.View
import com.example.angler_diary.R
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.databinding.FragmentFormBinding
import com.example.angler_diary.logic.form.actionHandler.FormActionHandler
import com.example.angler_diary.ui.form.FormManager

abstract class EditFormManager (
    protected val id: Int,
    protected val viewModel: DatabaseViewModel,
    goBackCallback: () -> Unit,
    context: Context,
    binding: FragmentFormBinding,
) : FormManager(binding, goBackCallback, context) {
    override fun performInitialiseActions(root: View) {
        createAddAction(root)
        createDeleteAction(root)
    }

    abstract fun createEditActionHandler(): FormActionHandler
    abstract fun createDeleteActionHandler(): FormActionHandler

    private fun createAddAction(root: View){
        val btn = root.findViewById<View>(R.id.saveBtn)
        btn.setOnClickListener {
            handleActionClick(createEditActionHandler())
        }
        btn.visibility = View.VISIBLE
    }

    private fun createDeleteAction(root: View){
        val btn = root.findViewById<View>(R.id.deleteBtn)
        btn.setOnClickListener {
            handleActionClick(createDeleteActionHandler())
        }
        btn.visibility = View.VISIBLE
    }
}