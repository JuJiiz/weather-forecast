package com.jujiiz.weatherforecast.data.weather

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class HourlyWeatherRaw(
    val main: MainWeatherRaw,
    @SerializedName("dt_txt") val dateTimeText: String,
)