package ro.bcsolutions.homemenu.ui.home_menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeMenuViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Menu Items here !"
    }
    val text: LiveData<String> = _text
}