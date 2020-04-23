package com.pixabay.utils.base

import io.reactivex.disposables.CompositeDisposable
import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

abstract class BaseViewModel : ViewModel() {


    val compositeDisposable: CompositeDisposable = CompositeDisposable()

//    private var mNavigator: WeakReference<N?>? = null
//
//    var navigator: N?
//        get() = mNavigator?.get()
//        set(navigator) {
//            this.mNavigator = WeakReference(navigator)
//        }

    open fun onViewCreated( ) {}
    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }


}