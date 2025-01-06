package com.example.angler_diary.ui.form

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.angler_diary.databinding.FragmentFormBinding
import com.example.angler_diary.logic.form.actionHandler.FormActionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class FormManager(
    private val binding: FragmentFormBinding,
    private val goBackCallback: () -> Unit,
    protected val context: Context
) {
    fun initialiseInputs() {
        (context as? LifecycleOwner)?.lifecycleScope?.launch {
            // performInitialiseInputs can be suspend and access some data with dispatchers.IO
            // and use it in UI thread later
            performInitialiseInputs(binding.inputsRoot)
        } ?: throw IllegalStateException("Context is not a LifecycleOwner")
    }

    abstract suspend fun performInitialiseInputs(root: ViewGroup)

    fun initialiseActions(){
        performInitialiseActions(binding.actionsRoot)
    }
    abstract fun performInitialiseActions(root: View)

    protected fun handleActionClick(handler: FormActionHandler) {
        // Ensure this is called in a context where `lifecycleScope` is available
        (context as? LifecycleOwner)?.lifecycleScope?.launch {
            // Switch to IO dispatcher for suspend function
            val result = withContext(Dispatchers.IO) {
                Log.d("FormManager", "executing handler in IO thread")
                handler.execute()
            }

            if (result.success) {
                goBackCallback()
            } else {
                showError(result.message ?: "Undefined error")
            }
        } ?: throw IllegalStateException("Context is not a LifecycleOwner")
    }

    private fun showError(message: String) {
        binding.errorText.text = message
    }
}