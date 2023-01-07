package com.jujiiz.weatherforecast.domain.weather

sealed class TemperatureUnit {
    object Celsius : TemperatureUnit()
    object Fahrenheit : TemperatureUnit()

    companion object {
        fun convertKelvinToCelsius(kelvin: Double): Double {
            return kelvin - 273.15
        }

        fun convertKelvinToFahrenheit(kelvin: Double): Double {
            return (kelvin - 273.15) * 9 / 5 + 32
        }
    }
}