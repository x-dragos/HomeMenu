package ro.bcsolutions.homemenu.database

import java.text.SimpleDateFormat
import androidx.room.TypeConverter

import java.util.*

class Converters {
    companion object {
        //"yyyy-MM-dd HH:mm:ss.SSSZ"
        private val formatter = SimpleDateFormat("yyyy-MM-dd")

        @TypeConverter
        @JvmStatic
        fun toCalendar(value: String?): Calendar? {
            if (value == null) return null
            val cal = Calendar.getInstance()
            cal.time = formatter.parse(value) ?: return null
            return cal
        }

        @TypeConverter
        @JvmStatic
        fun fromCalendar(date: Calendar?): String? {
            if (date == null) return null

            val dateCal = date.time
            return formatter.format(dateCal)
        }
    }
}