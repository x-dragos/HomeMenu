package ro.bcsolutions.homemenu.ui.menu_item

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ro.bcsolutions.homemenu.database.HomeMenuItemDao
import java.lang.IllegalArgumentException

class MenuItemViewModelFactory(private val homeMenuItemDao: HomeMenuItemDao, private val application: Application) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuItemViewModel::class.java)) {
            return MenuItemViewModel(homeMenuItemDao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}