package ro.bcsolutions.homemenu.ui.menu_item

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ro.bcsolutions.homemenu.database.Converters
import ro.bcsolutions.homemenu.database.HomeMenuItemDao
import ro.bcsolutions.homemenu.database.MenuItem
import java.util.*

class MenuItemViewModel(val homeMenuItemDao: HomeMenuItemDao) : ViewModel() {

    private val _menuDate = MutableLiveData<Calendar>()

    val menuDate: LiveData<Calendar>
            get() = _menuDate

    var menuLunch = String()

    var menuDinner = String()

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        initializeMenuDate()
    }

    private fun initializeMenuDate() {
        uiScope.launch {
            _menuDate.value = getNextMenuDate()
        }
    }

    private suspend fun getNextMenuDate(): Calendar {
        return withContext(Dispatchers.IO) {
            var menuDate = homeMenuItemDao.getLastMenuDate()
            if (menuDate != null ) {
                menuDate.add(Calendar.DAY_OF_MONTH, 1)
            } else {
                menuDate = Calendar.getInstance()
            }
            menuDate as Calendar
        }
    }

    fun setMenuDate(year: Int, month: Int, day: Int) {
        (_menuDate.value)?.set(year,month,day)
    }

    fun saveMenuItem() {
        if (menuDate.value == null || ( menuLunch.isEmpty() && menuDinner.isEmpty())) return
        else {
            uiScope.launch {
                val menuItem = menuDate.value?.let { MenuItem(date = it, lunch = menuLunch, dinner = menuDinner ) }
                menuItem?.let {
                    insertMenuItem(menuItem)
                }
            }
        }
        Log.i("MenuItemViewModel", "PassedData: $menuLunch, $menuDinner, ${Converters.fromCalendar(menuDate.value)}")
    }

    private suspend fun insertMenuItem(menuItem: MenuItem) {
        withContext(Dispatchers.IO) {
            homeMenuItemDao.insert(menuItem)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
