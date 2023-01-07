package com.jujiiz.weatherforecast.presentation.weather

import com.jujiiz.weatherforecast.domain.weather.TemperatureUnit

data class WeatherViewEntity(
    val locationName: String,
    val temperature: Double,
    val unit: TemperatureUnit,
    val feelsLike: Double,
    val humidity: Int,
    val date:String = "",
    val time:String = "",
)