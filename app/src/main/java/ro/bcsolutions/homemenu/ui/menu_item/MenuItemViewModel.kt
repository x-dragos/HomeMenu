package ro.bcsolutions.homemenu.ui.menu_item

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.lifecycle.ViewModel
import java.util.*

class MenuItemViewModel : ViewModel() {
    /*
    var date: String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())
    } else {
        java.text.SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis()+100000000)
    }*/

    var menuDate: Calendar = Calendar.getInstance();

    // TODO: Implement the ViewModel
}
