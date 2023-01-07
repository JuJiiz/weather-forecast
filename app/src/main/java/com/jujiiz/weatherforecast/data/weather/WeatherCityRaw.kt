package com.jujiiz.weatherforecast.data.weather

import androidx.annotation.Keep

@Keep
data class WeatherCityRaw(
    val id: Int,
    val name: String,
    val coord: CoordWeatherRaw,
    val country: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Int,
    val sunset: Int
)