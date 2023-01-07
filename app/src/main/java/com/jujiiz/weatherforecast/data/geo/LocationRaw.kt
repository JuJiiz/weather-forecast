package com.jujiiz.weatherforecast.data.geo

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class LocationRaw(
    @SerializedName("name") val name: String,
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double,
)