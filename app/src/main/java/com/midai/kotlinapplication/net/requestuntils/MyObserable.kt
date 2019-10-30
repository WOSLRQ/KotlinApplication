package com.midai.kotlinapplication.net.requestuntils

import android.util.Log
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class MyObserable<T> : Observer<T>{


//    override fun onNext(t: T) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

    override fun onError(e: Throwable) {
        Log.e("onError",e.message)
    }

    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {

    }
}