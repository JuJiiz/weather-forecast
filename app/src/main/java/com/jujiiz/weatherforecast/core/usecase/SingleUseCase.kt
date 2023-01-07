package com.jujiiz.weatherforecast.core.usecase

import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<Input, Output> {

    fun execute(input: Input): Single<Output> = buildUseCase(input)
        .compose(applySchedulers())

    protected abstract fun buildUseCase(input: Input): Single<Output>

    private fun applySchedulers(): SingleTransformer<Output, Output> =
        SingleTransformer {
            return@SingleTransformer it
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
}