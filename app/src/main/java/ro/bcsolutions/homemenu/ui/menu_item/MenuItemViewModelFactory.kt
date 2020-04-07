package ro.bcsolutions.homemenu.ui.menu_item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class MenuItemViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MenuItemViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}