package com.jujiiz.weatherforecast.core

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

abstract class BaseReactiveStore<Type> {

    private val subject = BehaviorSubject.create<Container<Type>>()

    private val all by lazy {
        subject.toFlowable(BackpressureStrategy.LATEST)
            .filter { it.containedValue != null }
            .map { it.containedValue!! }
            .subscribeOn(Schedulers.computation())
    }

    open fun retrieve(): Flowable<Type> = all

    @get:Synchronized
    var value: Type?
        get() = subject.value?.containedValue
        set(value) = subject.onNext(Container(value))
}

data class Container<T>(val containedValue: T?)