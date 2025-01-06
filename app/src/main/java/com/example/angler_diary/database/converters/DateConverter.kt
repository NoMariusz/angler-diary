package com.example.angler_diary.database.converters

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    // convert utils
    companion object {
        private const val DATE_FORMAT = "dd-MM-yyyy HH:mm:ss" // Define your desired date format
        private val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())

        // Convert Date to String
        fun dateToString(date: Date?): String? {
            return date?.let {
                dateFormat.format(it)
            }
        }

        // Convert String to Date
        fun stringToDate(dateString: String?): Date? {
            return try {
                dateString?.let {
                    dateFormat.parse(it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null // Return null if parsing fails
            }
        }
    }
}
