package ro.bcsolutions.homemenu.ui.edit_menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ro.bcsolutions.homemenu.database.HomeMenuItemDao
import ro.bcsolutions.homemenu.database.MenuItem

class EditMenuViewModel(homeMenuItemDao: HomeMenuItemDao) : ViewModel() {

    val menuItems: LiveData<List<MenuItem>?> = homeMenuItemDao.getAll()

    private val _navigateToMenuItemFragment = MutableLiveData<Long>()
    val navigateToMenuItemFragment: LiveData<Long?>
        get() = _navigateToMenuItemFragment

    fun onFabAddMenuItemClicked() {
        _navigateToMenuItemFragment.value = 0L
    }

    fun onMenuItemClicked(id: Long) {
        _navigateToMenuItemFragment.value = id
    }

    fun onMenuItemFragmentNavigated() {
        _navigateToMenuItemFragment.value = null
    }
}