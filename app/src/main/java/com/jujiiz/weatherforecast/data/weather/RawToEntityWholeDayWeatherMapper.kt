package com.jujiiz.weatherforecast.data.weather

import com.jujiiz.weatherforecast.domain.weather.HourlyWeatherEntity
import com.jujiiz.weatherforecast.domain.weather.WeatherCityEntity
import com.jujiiz.weatherforecast.domain.weather.WholeDayWeatherEntity
import io.reactivex.functions.Function
import javax.inject.Inject

class RawToEntityWholeDayWeatherMapper @Inject constructor(
    private val rawToEntityHourlyWeatherMapper: RawToEntityHourlyWeatherMapper,
) : Function<WholeDayWeatherResponseRaw, WholeDayWeatherEntity> {

    override fun apply(raw: WholeDayWeatherResponseRaw): WholeDayWeatherEntity {
        val maxItemThatShouldShow = 24 / 3 // 24 Hours with 3 hours step
        var weathers = listOf<HourlyWeatherEntity>()
        raw.list.forEachIndexed { index, hourly ->
            if (index >= maxItemThatShouldShow) return@forEachIndexed
            weathers = weathers + rawToEntityHourlyWeatherMapper.apply(hourly)
        }

        val city = WeatherCityEntity(
            name = raw.city.name,
        )
        return WholeDayWeatherEntity(
            weathers = weathers,
            city = city,
        )
    }
}