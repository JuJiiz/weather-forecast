package com.jujiiz.weatherforecast.presentation.weather.whole_day

import com.jujiiz.weatherforecast.domain.weather.WeatherCityEntity
import com.jujiiz.weatherforecast.presentation.weather.WeatherViewEntity

data class WholeDayWeatherViewEntity(
    val weathers: List<WeatherViewEntity>,
    val city: WeatherCityEntity,
)