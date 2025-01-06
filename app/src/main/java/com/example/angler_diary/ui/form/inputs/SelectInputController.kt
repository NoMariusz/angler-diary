package com.example.angler_diary.ui.form.inputs

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.textfield.MaterialAutoCompleteTextView


class SelectInputController(private val context: Context) {
    @SuppressLint("SetTextI18n")
    fun create(
        value: Int?,
        options: List<RelationOption>
    ): Pair<EditText, LinearLayout> {
        // Create a TextInputLayout for storing all inputs
        val inputsRoot = LinearLayout(context)
        // set inputsRoot LinearLayout width
        inputsRoot.layoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        inputsRoot.orientation = LinearLayout.VERTICAL


        // edittext for storing an id
        val input = EditText(context)
        input.width = ViewGroup.LayoutParams.MATCH_PARENT
        input.visibility = View.GONE
        // set default value
        input.setText(value.toString())

        inputsRoot.addView(input)


        // Create an AutoCompleteTextView for the dropdown
        val autoCompleteTextView = MaterialAutoCompleteTextView(context)

        // Map options to a list of display texts
        val optionsMap = options.associateBy { it.id } // Map of text -> RelationOption
        val displayOptions = options.map { it.text }  // List of option texts

        // Pre-select the current value if it exists in options
        val currentValueText = optionsMap[value]?.text
        autoCompleteTextView.setText(currentValueText, false)

        // Set up the adapter for the dropdown
        val adapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, displayOptions)
        autoCompleteTextView.setAdapter(adapter)

        // Set a listener to handle selection changes
        autoCompleteTextView.setOnItemClickListener { _, view, _, _ ->
            val selectedId = options.first { it.text == (view as TextView).text }.id
            input.setText(selectedId.toString())
        }

        autoCompleteTextView.width = ViewGroup.LayoutParams.MATCH_PARENT

        inputsRoot.addView(autoCompleteTextView)


        return Pair(input, inputsRoot)
    }
}