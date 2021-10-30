package pl.polsl.timemanager.base

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseAndroidViewModel(application: Application): AndroidViewModel(application) {

    protected val compositeDisposable = CompositeDisposable()
    val error: LiveData<String> by lazy {
        _error
    }

    private val _error = MutableLiveData<String>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    protected fun onError(t: Throwable) {
        _error.value = t.message
    }

}