package com.jujiiz.weatherforecast.data.weather

import com.jujiiz.weatherforecast.domain.weather.HourlyWeatherEntity
import io.reactivex.functions.Function
import javax.inject.Inject

class RawToEntityHourlyWeatherMapper @Inject constructor() :
    Function<HourlyWeatherRaw, HourlyWeatherEntity> {

    override fun apply(raw: HourlyWeatherRaw): HourlyWeatherEntity {
        val split = raw.dateTimeText.split(" ")
        val date = split[0]
        val timeHHmm = split[1]
            .split(":")
            .let { "${it[0]}:${it[1]}" }

        return HourlyWeatherEntity(
            temperature = raw.main.temp, // Kevin
            feelsLike = raw.main.feelsLike, // Kevin
            humidity = raw.main.humidity,
            date = date,
            time = timeHHmm,
        )
    }
}