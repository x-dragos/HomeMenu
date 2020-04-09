package ro.bcsolutions.homemenu

import java.text.SimpleDateFormat
import androidx.room.TypeConverter

import java.util.*

class Converters {
    companion object {
        private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ")

        @TypeConverter
        @JvmStatic
        fun toCalendar(value: String?): Calendar? {
            return value?.let {
                val cal = Calendar.getInstance();
                cal.time = formatter.parse(it)
                return cal
            }
        }

        @TypeConverter
        @JvmStatic
        fun fromCalendar(date: Calendar?): String? {
            val dateCal = date!!.time

            return formatter.format(dateCal)
        }
    }
}