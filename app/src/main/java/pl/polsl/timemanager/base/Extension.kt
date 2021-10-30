package pl.polsl.timemanager.base

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

fun <T> CompositeDisposable.subscribeAndPush(single: Single<T>,
                                             onError: ((t: Throwable) -> Unit)? = null,
                                             onSuccess: (t: T) -> Unit): CompositeDisposable {

    lateinit var disposable: Disposable

    disposable = single.doAfterTerminate {
        this.remove(disposable)
    }
    .subscribe(onSuccess, onError ?: {})

    this.add(disposable)

    return this
}