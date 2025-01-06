package com.example.angler_diary.ui.form.inputs

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.example.angler_diary.database.converters.DateConverter
import java.util.Date

class InputsController(
    private val rootView: ViewGroup, private val context: Context
) {
    private val inputs: MutableMap<String, EditText> = HashMap()

    fun getString(name: String): String? {
        val value = inputs[name]?.text ?: return null
        return value.toString()
    }

    fun getDate(name: String): Date? {
        val value = inputs[name]?.text ?: return null
        return DateConverter.stringToDate(value.toString())
    }

    fun getInt(name: String): Int? {
        val value = inputs[name]?.text ?: return null
        if(value.isBlank()) return null

        return value.toString().toInt()
    }

    fun create(name: String, label: String, value: String?) {
        val input = createText(name, label, value)

        inputs[name] = input
        createField(input, label)
    }

    fun create(name: String, label: String, value: Date?) {
        val input = DateInputController(context).create(label, value)

        inputs[name] = input
        createField(input, label)
    }

    fun create(name: String, label: String, value: Int?, options: List<RelationOption>) {
        val res = SelectInputController(context).create(value, options)

        inputs[name] = res.first
        createField(res.first, label, res.second)
    }

    private fun createText(name: String, label: String, value: String?): EditText {
        val input = EditText(context)
        if (value != null) {
            input.setText(value)
        }
        return input
    }


    private fun createField(input: EditText, label: String, wrapper: View? = null) {
        val container = LinearLayout(context)
        container.orientation = LinearLayout.VERTICAL

        val labelView = TextView(context)
        labelView.text = label
        container.addView(labelView)

        if(wrapper == null){
            input.width = ViewGroup.LayoutParams.MATCH_PARENT
        }

        container.addView(wrapper ?: input)

        rootView.addView(container)
    }
}