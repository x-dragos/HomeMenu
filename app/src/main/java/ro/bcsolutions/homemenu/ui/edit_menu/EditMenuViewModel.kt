package ro.bcsolutions.homemenu.ui.edit_menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ro.bcsolutions.homemenu.database.HomeMenuItemDao

class EditMenuViewModel(homeMenuItemDao: HomeMenuItemDao) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Edit Menu here!!!"
    }
    val text: LiveData<String> = _text
}