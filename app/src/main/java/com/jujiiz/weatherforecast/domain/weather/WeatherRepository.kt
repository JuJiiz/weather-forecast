package com.jujiiz.weatherforecast.domain.weather

import com.jujiiz.weatherforecast.domain.geo.LatLngEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface WeatherRepository {
    fun setSelectedTemperatureUnit(unit: TemperatureUnit): Completable

    fun retrieveSelectedTemperatureUnit(): Flowable<TemperatureUnit>

    fun requestWeather(latLng: LatLngEntity): Single<WeatherEntity>

    fun requestWholeDayWeather(latLng: LatLngEntity): Single<WholeDayWeatherEntity>
}