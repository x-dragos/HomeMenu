package ro.bcsolutions.homemenu.ui.edit_menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditMenuViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Edit Menu here!!!"
    }
    val text: LiveData<String> = _text
}