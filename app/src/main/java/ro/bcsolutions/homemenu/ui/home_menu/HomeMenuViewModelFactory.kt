package ro.bcsolutions.homemenu.ui.home_menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ro.bcsolutions.homemenu.database.HomeMenuItemDao
import java.lang.IllegalArgumentException

class HomeMenuViewModelFactory(private val homeMenuItemDao: HomeMenuItemDao): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeMenuViewModel::class.java)) {
            return HomeMenuViewModel(homeMenuItemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}