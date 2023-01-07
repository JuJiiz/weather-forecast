package com.jujiiz.weatherforecast.presentation.weather

import com.jujiiz.weatherforecast.domain.weather.TemperatureUnit
import com.jujiiz.weatherforecast.domain.weather.WeatherEntity
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class WeatherViewMapper @Inject constructor() :
    BiFunction<WeatherEntity, TemperatureUnit, WeatherViewEntity> {

    override fun apply(weather: WeatherEntity, unit: TemperatureUnit): WeatherViewEntity {
        val temperature = when (unit) {
            TemperatureUnit.Celsius -> convertKelvinToCelsius(weather.temperature)
            TemperatureUnit.Fahrenheit -> convertKelvinToFahrenheit(weather.temperature)
        }

        val feelsLike = when (unit) {
            TemperatureUnit.Celsius -> convertKelvinToCelsius(weather.feelsLike)
            TemperatureUnit.Fahrenheit -> convertKelvinToFahrenheit(weather.feelsLike)
        }

        return WeatherViewEntity(
            locationName = weather.locationName,
            temperature = temperature,
            unit = unit,
            feelsLike = feelsLike,
            humidity = weather.humidity,
        )
    }

    private fun convertKelvinToCelsius(kelvin: Double): Double {
        return kelvin - 272.15
    }

    private fun convertKelvinToFahrenheit(kelvin: Double): Double {
        return 1.8 * (kelvin - 273) + 32
    }
}