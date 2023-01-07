package com.jujiiz.weatherforecast.domain.weather

import com.jujiiz.weatherforecast.core.usecase.FlowableUseCase
import io.reactivex.Flowable
import javax.inject.Inject

class RetrieveSelectedTemperatureUnitUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
) : FlowableUseCase<TemperatureUnit>() {

    override fun buildUseCase(): Flowable<TemperatureUnit> {
        return weatherRepository.retrieveSelectedTemperatureUnit()
    }
}