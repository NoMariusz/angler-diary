package com.example.angler_diary.ui.form

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class InputsController(
    private val rootView: ViewGroup,
    private val context: Context
) {
    private val inputs: MutableMap<String, EditText> = HashMap()

    fun getValue(name: String): String?{
        val value = inputs[name]?.text ?: return null
        return value.toString()
    }

    fun create(name: String, label: String, value: String?){
        createText(name, label, value)
    }

    private fun createText(name: String, label: String, value: String?){
        val input = EditText(context)
        if(value != null){
            input.setText(value)
        }
        inputs[name] = input

        createField(input, label)
    }

    private fun createField(input: EditText, label: String){
        val container = LinearLayout(context)
        container.orientation = LinearLayout.VERTICAL

        val labelView = TextView(context)
        labelView.text = label
        container.addView(labelView)

        input.width = ViewGroup.LayoutParams.MATCH_PARENT
        container.addView(input)

        rootView.addView(container)
    }
}