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

class MenuItemViewModel(val homeMenuItemDao: HomeMenuItemDao, val menuItemId: Long) : ViewModel() {

    var menuItem: MenuItem? = null

    private val _menuDate = MutableLiveData<Calendar>()

    val menuDate: LiveData<Calendar>
            get() = _menuDate

    var menuLunch = MutableLiveData<String>()

    var menuDinner = MutableLiveData<String>()

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        if (menuItemId > 0L)
        {
            uiScope.launch {
                menuItem = withContext(Dispatchers.IO) {
                    homeMenuItemDao.get(menuItemId)
                }
                menuItem?.let {
                    _menuDate.value = menuItem!!.date
                    menuLunch.value = menuItem!!.lunch
                    menuDinner.value = menuItem!!.dinner
                } ?: initializeMenuDate()
            }
        } else {
            initializeMenuDate()
        }
    }

    private fun initializeMenuDate() {
        uiScope.launch {
            _menuDate.value = getNextMenuDate()
        }
    }

    private suspend fun getNextMenuDate(): Calendar {
        return withContext(Dispatchers.IO) {
            var menuDate = homeMenuItemDao.getLastMenuDate()
            menuDate?.add(Calendar.DAY_OF_MONTH, 1) ?: run {
                menuDate = Calendar.getInstance()
            }
            menuDate as Calendar
        }
    }

    fun setMenuDate(year: Int, month: Int, day: Int) {
        (_menuDate.value)?.set(year,month,day)
    }

    fun saveMenuItem() {
        if (menuDate.value == null || ( menuLunch.value?.isEmpty() == true && menuDinner.value?.isEmpty() == true)) {
            return
        }
        else {
            uiScope.launch {
                menuItem?.let {
                    it.date = menuDate.value!!
                    it.lunch = menuLunch.value
                    it.dinner = menuDinner.value
                } ?: run {
                    menuItem = menuDate.value?.let { MenuItem(date = it, lunch = menuLunch.value, dinner = menuDinner.value ) }
                }
                menuItem!!.let { menuItem
                    if (menuItemId > 0L) {
                        withContext(Dispatchers.IO) {
                            homeMenuItemDao.update(menuItem!!)
                        }
                    } else {
                        withContext(Dispatchers.IO) {
                            homeMenuItemDao.insert(menuItem!!)
                        }
                    }
                }
            }
        }
        //Log.i("MenuItemViewModel", "PassedData: $menuLunch, $menuDinner, ${Converters.fromCalendar(menuDate.value)}")
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
