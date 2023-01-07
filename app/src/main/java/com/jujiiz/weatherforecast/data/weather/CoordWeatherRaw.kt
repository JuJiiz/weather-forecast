package com.jujiiz.weatherforecast.data.weather

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CoordWeatherRaw(
    val lon: Double,
    val lat: Double
)