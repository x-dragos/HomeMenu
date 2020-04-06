package ro.bcsolutions.homemenu.ui.menu_item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MenuItemViewModel : ViewModel() {
    fun setMenuDate(year: Int, month: Int, day: Int) {
        (_menuDate.value)?.set(year,month,day)
    }

    private val _menuDate = MutableLiveData<Calendar>()

    val menuDate: LiveData<Calendar>
            get() = _menuDate

    init {
        _menuDate.value = Calendar.getInstance()
        (_menuDate.value)?.set(2020,4,5)
    }
}
