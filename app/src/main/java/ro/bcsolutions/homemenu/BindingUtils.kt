package ro.bcsolutions.homemenu

import android.widget.TextView
import androidx.databinding.BindingAdapter
import ro.bcsolutions.homemenu.database.MenuItem
import java.text.SimpleDateFormat

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
    }
}