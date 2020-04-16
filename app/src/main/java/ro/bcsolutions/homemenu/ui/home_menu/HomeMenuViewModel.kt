package ro.bcsolutions.homemenu.ui.home_menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ro.bcsolutions.homemenu.database.HomeMenuItemDao
import ro.bcsolutions.homemenu.database.MenuItem

class HomeMenuViewModel(homeMenuItemDao: HomeMenuItemDao) : ViewModel() {

    val menuItems: LiveData<List<MenuItem>?> = homeMenuItemDao.getAll()
}