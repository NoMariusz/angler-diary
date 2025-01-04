package com.example.angler_diary.ui.form

import android.view.View
import android.view.ViewGroup

interface FormManager {
    fun initialiseInputs(root: ViewGroup)
    fun initialiseActions(root: View)
}