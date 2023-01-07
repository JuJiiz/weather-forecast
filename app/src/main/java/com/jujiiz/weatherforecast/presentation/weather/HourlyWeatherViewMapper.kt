package com.jujiiz.weatherforecast.presentation.weather

import com.jujiiz.weatherforecast.domain.weather.HourlyWeatherEntity
import com.jujiiz.weatherforecast.domain.weather.TemperatureUnit
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class HourlyWeatherViewMapper @Inject constructor() :
    BiFunction<HourlyWeatherEntity, TemperatureUnit, WeatherViewEntity> {

    override fun apply(weather: HourlyWeatherEntity, unit: TemperatureUnit): WeatherViewEntity {
        val temperature = when (unit) {
            TemperatureUnit.Celsius -> TemperatureUnit.convertKelvinToCelsius(weather.temperature)
            TemperatureUnit.Fahrenheit -> TemperatureUnit.convertKelvinToFahrenheit(weather.temperature)
        }

        val feelsLike = when (unit) {
            TemperatureUnit.Celsius -> TemperatureUnit.convertKelvinToCelsius(weather.feelsLike)
            TemperatureUnit.Fahrenheit -> TemperatureUnit.convertKelvinToFahrenheit(weather.feelsLike)
        }

        return WeatherViewEntity(
            locationName = "",
            temperature = temperature,
            unit = unit,
            feelsLike = feelsLike,
            humidity = weather.humidity,
            date = weather.date,
            time = weather.time,
        )
    }
}