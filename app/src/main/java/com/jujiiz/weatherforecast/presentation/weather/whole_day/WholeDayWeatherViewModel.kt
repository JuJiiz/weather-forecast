package com.jujiiz.weatherforecast.presentation.weather.whole_day

import androidx.lifecycle.MutableLiveData
import com.jujiiz.weatherforecast.core.BaseViewModel
import com.jujiiz.weatherforecast.core.lazySubscription
import com.jujiiz.weatherforecast.domain.geo.RetrieveSelectedLocationUseCase
import com.jujiiz.weatherforecast.domain.weather.RequestWholeDayWeatherByLatLngUseCase
import com.jujiiz.weatherforecast.domain.weather.RetrieveSelectedTemperatureUnitUseCase
import com.jujiiz.weatherforecast.domain.weather.WholeDayWeatherEntity
import io.reactivex.BackpressureStrategy
import io.reactivex.rxkotlin.Flowables
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class WholeDayWeatherViewModel @Inject constructor(
    private val retrieveSelectedLocationUseCase: RetrieveSelectedLocationUseCase,
    private val requestWholeDayWeatherByLatLngUseCase: RequestWholeDayWeatherByLatLngUseCase,
    private val retrieveSelectedTemperatureUnitUseCase: RetrieveSelectedTemperatureUnitUseCase,
    private val wholeDayWeatherViewMapper: WholeDayWeatherViewMapper,
) : BaseViewModel() {
    private val streamWeather: PublishSubject<WholeDayWeatherEntity> = PublishSubject.create()

    val liveWeather: MutableLiveData<WholeDayWeatherViewEntity> = MutableLiveData()

    init {
        retrieveLocation()
        combineWeatherWithUnit()
    }

    private fun retrieveLocation() {
        compositeDisposable += retrieveSelectedLocationUseCase.execute()
            .flatMapSingle {
                requestWholeDayWeatherByLatLngUseCase.execute(it)
                    .subscribeOn(Schedulers.io())
            }
            .doOnNext(streamWeather::onNext)
            .lazySubscription("retrieve location")
    }

    private fun combineWeatherWithUnit() {
        compositeDisposable += Flowables.combineLatest(
            streamWeather.toFlowable(BackpressureStrategy.LATEST),
            retrieveSelectedTemperatureUnitUseCase.execute(),
            wholeDayWeatherViewMapper::apply,
        )
            .doOnNext(liveWeather::postValue)
            .lazySubscription("combine weather with unit")
    }
}