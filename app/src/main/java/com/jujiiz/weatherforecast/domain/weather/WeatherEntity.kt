package com.jujiiz.weatherforecast.domain.weather

import com.jujiiz.weatherforecast.domain.geo.LatLngEntity

data class WeatherEntity(
    val temperature: Double,
    val feelsLike: Double,
    val humidity: Int,
    val latLng: LatLngEntity,
    val locationName: String,
)