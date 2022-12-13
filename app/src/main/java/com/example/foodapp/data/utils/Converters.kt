package com.example.foodapp.data.utils

import androidx.room.TypeConverter
import java.util.*

class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromTimestampToDate(value: Long): Date {
            return Date(value)
        }
        @TypeConverter
        @JvmStatic
        fun dateToTimestamp(date: Date): Long {
            return date.time
        }
    }
}