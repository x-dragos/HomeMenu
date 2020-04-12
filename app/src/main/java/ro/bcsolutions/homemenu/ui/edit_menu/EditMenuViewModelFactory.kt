package ro.bcsolutions.homemenu.ui.edit_menu

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ro.bcsolutions.homemenu.database.HomeMenuItemDao
import java.lang.IllegalArgumentException

class EditMenuViewModelFactory(private val homeMenuItemDao: HomeMenuItemDao) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditMenuViewModel::class.java)) {
            return EditMenuViewModel(homeMenuItemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}