package com.jujiiz.weatherforecast.core.usecase

import io.reactivex.Completable
import io.reactivex.CompletableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<Input> {

    fun execute(input: Input): Completable = buildUseCase(input)
        .compose(applySchedulers())

    protected abstract fun buildUseCase(input: Input): Completable

    private fun applySchedulers(): CompletableTransformer =
        CompletableTransformer {
            return@CompletableTransformer it
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
}