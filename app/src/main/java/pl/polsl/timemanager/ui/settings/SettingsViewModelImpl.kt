package pl.polsl.timemanager.ui.settings

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import pl.polsl.timemanager.api.RetrofitProvider

class SettingsViewModelImpl(application: Application) : SettingsViewModel(application) {

    override val logout = MutableLiveData<Any>()

    private val sharedPreferences = getApplication<Application>()
                    .getSharedPreferences("app_session", Context.MODE_PRIVATE)

    override fun logout() {
        sharedPreferences.edit().run {
            remove("token")
            apply()
        }
        RetrofitProvider.setToken(null)
        logout.value = Any()
    }

}