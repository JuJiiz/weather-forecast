package com.jujiiz.weatherforecast.domain.weather

import com.jujiiz.weatherforecast.core.usecase.SingleUseCase
import com.jujiiz.weatherforecast.domain.geo.LatLngEntity
import io.reactivex.Single
import javax.inject.Inject

class RequestWholeDayWeatherByLatLngUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
) : SingleUseCase<LatLngEntity, WholeDayWeatherEntity>() {

    override fun buildUseCase(input: LatLngEntity): Single<WholeDayWeatherEntity> {
        return weatherRepository.requestWholeDayWeather(input)
    }
}