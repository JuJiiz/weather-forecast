package com.jujiiz.weatherforecast.data.weather

import androidx.annotation.Keep

@Keep
data class WholeDayWeatherResponseRaw(
    val list: List<HourlyWeatherRaw>,
    val city: WeatherCityRaw,
)