package pl.polsl.timemanager

import android.app.Application
import io.reactivex.rxjava3.plugins.RxJavaPlugins

class TimeManagerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler {}
    }

}