package com.jujiiz.weatherforecast.domain.weather

import com.jujiiz.weatherforecast.core.usecase.CompletableUseCase
import io.reactivex.Completable
import javax.inject.Inject

class RequestSetSelectedTemperatureUnitUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
) : CompletableUseCase<TemperatureUnit>() {

    override fun buildUseCase(input: TemperatureUnit): Completable {
        return weatherRepository.setSelectedTemperatureUnit(input)
    }
}