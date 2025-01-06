package com.example.angler_diary.ui.form.inputs

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.EditText
import com.example.angler_diary.database.converters.DateConverter
import java.util.Calendar
import java.util.Date

class DateInputController(private val context: Context) {
    fun create(label: String, value: Date?): EditText {
        val input = EditText(context)
        input.inputType = EditText.AUTOFILL_TYPE_DATE
        input.inputType = 0 // Disable keyboard input
        input.isFocusable = false // Prevent typing, only allow clicking
        input.hint = label

        // Set the initial value if present
        value?.let {
            input.setText(DateConverter.dateToString(it))
        }

        // Set an OnClickListener to open the date and time picker dialogs
        input.setOnClickListener {
            showDateTimePickerDialog(value) { selectedDate ->
                // Set the selected date in the input field
                input.setText(DateConverter.dateToString(selectedDate))
            }
        }

        return input
    }

    private fun showDateTimePickerDialog(
        initialDate: Date? = null,
        onDateTimeSelected: (Date) -> Unit
    ) {
        // Use the initial date or the current date as the default
        val calendar = Calendar.getInstance()
        initialDate?.let {
            calendar.time = it
        }

        // Show DatePickerDialog first
        val datePickerDialog = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                // Set the selected date in the calendar
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                // Show TimePickerDialog after selecting the date
                TimePickerDialog(
                    context,
                    { _, hourOfDay, minute ->
                        // Set the selected time in the calendar
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        calendar.set(Calendar.MINUTE, minute)

                        // Call the callback with the final selected date and time
                        onDateTimeSelected(calendar.time)
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true // Use 24-hour format
                ).show()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }
}