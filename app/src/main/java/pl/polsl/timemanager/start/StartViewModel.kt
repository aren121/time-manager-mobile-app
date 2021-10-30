package pl.polsl.timemanager.start

import android.app.Application
import androidx.lifecycle.LiveData
import pl.polsl.timemanager.base.BaseAndroidViewModel
import pl.polsl.timemanager.model.Credentials

abstract class StartViewModel(application: Application): BaseAndroidViewModel(application) {

    abstract val loginStatus: LiveData<LoginStatus>

    abstract fun checkAlreadyLogged()

    abstract fun login(credentials: Credentials)

}