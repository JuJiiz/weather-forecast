package com.jujiiz.weatherforecast.data.weather

import androidx.annotation.Keep

@Keep
data class WeatherRaw(
    val main: MainWeatherRaw,
    val coord: CoordWeatherRaw,
    val name: String,
)