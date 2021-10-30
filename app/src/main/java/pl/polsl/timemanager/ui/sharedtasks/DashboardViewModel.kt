package pl.polsl.timemanager.ui.sharedtasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "TODO"
    }
    val text: LiveData<String> = _text
}