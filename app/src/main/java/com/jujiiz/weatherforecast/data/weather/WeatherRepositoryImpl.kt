package com.jujiiz.weatherforecast.data.weather

import com.jujiiz.weatherforecast.core.network.ApiService
import com.jujiiz.weatherforecast.domain.geo.LatLngEntity
import com.jujiiz.weatherforecast.domain.weather.TemperatureUnit
import com.jujiiz.weatherforecast.domain.weather.WeatherEntity
import com.jujiiz.weatherforecast.domain.weather.WeatherRepository
import com.jujiiz.weatherforecast.domain.weather.WholeDayWeatherEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val rawToEntityWeatherMapper: RawToEntityWeatherMapper,
    private val tempUnitStore: TempUnitStore,
    private val rawToEntityWholeDayWeatherMapper: RawToEntityWholeDayWeatherMapper,
) : WeatherRepository {

    override fun setSelectedTemperatureUnit(unit: TemperatureUnit): Completable {
        tempUnitStore.value = unit
        return Completable.complete()
    }

    override fun retrieveSelectedTemperatureUnit(): Flowable<TemperatureUnit> {
        return tempUnitStore.retrieve()
    }

    override fun requestWeather(latLng: LatLngEntity): Single<WeatherEntity> {
        return apiService.requestWeather(
            latLng.latitude,
            latLng.longitude,
        ).map(rawToEntityWeatherMapper)
    }

    override fun requestWholeDayWeather(latLng: LatLngEntity): Single<WholeDayWeatherEntity> {
        return apiService.requestWholeDayWeather(
            latLng.latitude,
            latLng.longitude,
        ).map(rawToEntityWholeDayWeatherMapper)
    }

}