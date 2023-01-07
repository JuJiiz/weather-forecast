package com.jujiiz.weatherforecast.presentation.weather.current

import androidx.lifecycle.MutableLiveData
import com.jujiiz.weatherforecast.core.BaseViewModel
import com.jujiiz.weatherforecast.core.lazySubscription
import com.jujiiz.weatherforecast.domain.geo.LatLngEntity
import com.jujiiz.weatherforecast.domain.geo.RequestLatLngByLocationNameUseCase
import com.jujiiz.weatherforecast.domain.geo.RequestSetSelectedLocationUseCase
import com.jujiiz.weatherforecast.domain.geo.RetrieveSelectedLocationUseCase
import com.jujiiz.weatherforecast.domain.weather.*
import com.jujiiz.weatherforecast.presentation.weather.WeatherViewEntity
import com.jujiiz.weatherforecast.presentation.weather.WeatherViewMapper
import io.reactivex.BackpressureStrategy
import io.reactivex.rxkotlin.Flowables
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class CurrentWeatherViewModel @Inject constructor(
    private val requestWeatherByLatLngUseCase: RequestWeatherByLatLngUseCase,
    private val requestSetSelectedTemperatureUnitUseCase: RequestSetSelectedTemperatureUnitUseCase,
    private val retrieveSelectedTemperatureUnitUseCase: RetrieveSelectedTemperatureUnitUseCase,
    private val weatherViewMapper: WeatherViewMapper,
    private val requestSetSelectedLocationUseCase: RequestSetSelectedLocationUseCase,
    private val retrieveSelectedLocationUseCase: RetrieveSelectedLocationUseCase,
    private val requestLatLngByLocationNameUseCase: RequestLatLngByLocationNameUseCase,
) : BaseViewModel() {
    private val streamWeather: PublishSubject<WeatherEntity> = PublishSubject.create()

    val liveWeather: MutableLiveData<WeatherViewEntity> = MutableLiveData()

    init {
        retrieveSelectedLocation()
        combineWeatherWithUnit()
    }

    private fun retrieveSelectedLocation() {
        compositeDisposable += retrieveSelectedLocationUseCase.execute()
            .flatMapSingle {
                requestWeatherByLatLngUseCase.execute(it)
                    .subscribeOn(Schedulers.io())
            }
            .doOnNext(streamWeather::onNext)
            .lazySubscription("retrieve location")
    }

    private fun combineWeatherWithUnit() {
        compositeDisposable += Flowables.combineLatest(
            streamWeather.toFlowable(BackpressureStrategy.LATEST),
            retrieveSelectedTemperatureUnitUseCase.execute(),
            weatherViewMapper::apply
        )
            .doOnNext(liveWeather::postValue)
            .lazySubscription("combine weather with unit")
    }

    fun updateLocation(latLngEntity: LatLngEntity) {
        compositeDisposable += requestSetSelectedLocationUseCase.execute(latLngEntity)
            .lazySubscription("update location")
    }

    fun switchTempUnit() {
        val input = when (liveWeather.value?.unit ?: TemperatureUnit.Celsius) {
            TemperatureUnit.Celsius -> TemperatureUnit.Fahrenheit
            TemperatureUnit.Fahrenheit -> TemperatureUnit.Celsius
        }
        compositeDisposable += requestSetSelectedTemperatureUnitUseCase.execute(input)
            .lazySubscription("set unit")
    }

    fun updateLocationByName(name: String) {
        compositeDisposable += requestLatLngByLocationNameUseCase.execute(name)
            .lazySubscription("request location of $name")
    }
}