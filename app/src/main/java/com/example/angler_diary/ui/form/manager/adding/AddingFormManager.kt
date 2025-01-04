package com.example.angler_diary.ui.form.manager.adding

import android.content.Context
import android.view.View
import com.example.angler_diary.R
import com.example.angler_diary.database.DatabaseViewModel
import com.example.angler_diary.logic.form.actionHandler.add.FormActionAddHandler
import com.example.angler_diary.ui.form.FormManager

abstract class AddingFormManager(
    protected val viewModel: DatabaseViewModel,
    protected val goBackCallback: () -> Unit,
    protected val context: Context
) : FormManager {
    override fun initialiseActions(root: View) {
        createAddAction(root)
    }

    abstract fun createAddActionHandler(): FormActionAddHandler

    private fun createAddAction(root: View){
        val btn = root.findViewById<View>(R.id.saveBtn)
        btn.setOnClickListener {
            // handler should be called inside of button click to have fresh entity with actual state
            val handler = createAddActionHandler()
            handler.execute()
        }
        btn.visibility = View.VISIBLE
    }
}