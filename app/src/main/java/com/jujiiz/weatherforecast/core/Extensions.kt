package com.jujiiz.weatherforecast.core

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.Disposable

fun <T> Single<T>.lazySubscription(message: String): Disposable {
    return subscribe({
        Log.d("SUCCESS", "$message response: $it")
    }, {
        Log.e("ERROR", "$message error: ${it.message}")
    })
}

fun Completable.lazySubscription(message: String): Disposable {
    return subscribe({
        Log.d("SUCCESS", message)
    }, {
        Log.e("ERROR", "$message error: ${it.message}")
    })
}

fun <T> Flowable<T>.lazySubscription(message: String): Disposable {
    return subscribe({
        Log.d("SUCCESS", "$message response: $it")
    }, {
        Log.e("ERROR", "$message error: ${it.message}")
    })
}