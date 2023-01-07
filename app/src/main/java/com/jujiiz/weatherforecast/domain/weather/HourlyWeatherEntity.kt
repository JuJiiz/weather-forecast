package com.jujiiz.weatherforecast.domain.weather

data class HourlyWeatherEntity(
    val temperature: Double,
    val feelsLike: Double,
    val humidity: Int,
    val date:String,
    val time:String,
)