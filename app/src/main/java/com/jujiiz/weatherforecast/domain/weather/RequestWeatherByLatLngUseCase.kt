package com.jujiiz.weatherforecast.domain.weather

import com.jujiiz.weatherforecast.core.usecase.SingleUseCase
import com.jujiiz.weatherforecast.domain.geo.LatLngEntity
import io.reactivex.Single
import javax.inject.Inject

class RequestWeatherByLatLngUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
) : SingleUseCase<LatLngEntity, WeatherEntity>() {

    override fun buildUseCase(input: LatLngEntity): Single<WeatherEntity> {
        return weatherRepository.requestWeather(input)
    }
}