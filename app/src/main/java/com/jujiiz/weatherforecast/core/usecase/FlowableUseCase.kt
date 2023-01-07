package com.jujiiz.weatherforecast.core.usecase

import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class FlowableUseCase<Output> {

    fun execute(): Flowable<Output> = buildUseCase()
        .compose(applySchedulers())

    protected abstract fun buildUseCase(): Flowable<Output>

    private fun applySchedulers(): FlowableTransformer<Output, Output> =
        FlowableTransformer {
            return@FlowableTransformer it
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
}