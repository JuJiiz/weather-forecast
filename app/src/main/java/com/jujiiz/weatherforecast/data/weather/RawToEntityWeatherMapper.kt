package com.jujiiz.weatherforecast.data.weather

import com.jujiiz.weatherforecast.domain.geo.LatLngEntity
import com.jujiiz.weatherforecast.domain.weather.WeatherEntity
import io.reactivex.functions.Function
import javax.inject.Inject

class RawToEntityWeatherMapper @Inject constructor() : Function<WeatherRaw, WeatherEntity> {

    override fun apply(raw: WeatherRaw): WeatherEntity {
        return WeatherEntity(
            temperature = raw.main.temp, // Kevin
            feelsLike = raw.main.feelsLike, // Kevin
            humidity = raw.main.humidity,
            latLng = LatLngEntity(
                latitude = raw.coord.lat,
                longitude = raw.coord.lon,
            ),
            locationName = raw.name,
        )
    }
}