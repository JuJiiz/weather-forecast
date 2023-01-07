package com.jujiiz.weatherforecast.presentation.weather.whole_day

import com.jujiiz.weatherforecast.domain.weather.TemperatureUnit
import com.jujiiz.weatherforecast.domain.weather.WholeDayWeatherEntity
import com.jujiiz.weatherforecast.presentation.weather.HourlyWeatherViewMapper
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class WholeDayWeatherViewMapper @Inject constructor(
    private val hourlyWeatherViewMapper: HourlyWeatherViewMapper,
) : BiFunction<WholeDayWeatherEntity, TemperatureUnit, WholeDayWeatherViewEntity> {

    override fun apply(
        wholeDay: WholeDayWeatherEntity,
        unit: TemperatureUnit
    ): WholeDayWeatherViewEntity {
        val weathers = wholeDay.weathers.map {
            hourlyWeatherViewMapper.apply(it, unit)
        }

        return WholeDayWeatherViewEntity(
            weathers = weathers,
            city = wholeDay.city,
        )
    }
}