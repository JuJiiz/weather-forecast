package com.jujiiz.weatherforecast.domain.weather

data class WholeDayWeatherEntity(
    val weathers: List<HourlyWeatherEntity>,
    val city: WeatherCityEntity,
)