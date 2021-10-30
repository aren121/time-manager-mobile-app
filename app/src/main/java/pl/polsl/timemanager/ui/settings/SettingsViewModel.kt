package pl.polsl.timemanager.ui.settings

import android.app.Application
import androidx.lifecycle.LiveData
import pl.polsl.timemanager.base.BaseAndroidViewModel

abstract class SettingsViewModel(application: Application) : BaseAndroidViewModel(application) {

    abstract val logout: LiveData<Any>

    abstract fun logout()

}