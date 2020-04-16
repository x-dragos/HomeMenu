package ro.bcsolutions.homemenu

import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter
import ro.bcsolutions.homemenu.database.MenuItem
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("shortFormatDateString")
fun TextView.setShortFormatDateString(item: MenuItem?) {
    item?.let {
        text = SimpleDateFormat("dd-MMMM").format(item.date.time)
    }
}

@BindingAdapter("weekdayString")
fun TextView.setWeekdayString(item: MenuItem?) {
    item?.let {
        text = SimpleDateFormat("EEE").format(item.date.time)
        if (item.date.get(Calendar.DAY_OF_WEEK) in arrayOf(1,7)) setTextColor(Color.RED) else setTextColor(Color.BLACK)
    }
}