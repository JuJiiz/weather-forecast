package com.jujiiz.weatherforecast.core.network

import com.jujiiz.weatherforecast.data.geo.LocationRaw
import com.jujiiz.weatherforecast.data.weather.WeatherRaw
import com.jujiiz.weatherforecast.data.weather.WholeDayWeatherResponseRaw
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("geo/1.0/direct")
    fun requestLocationByName(
        @Query("q") query: String,
    ): Single<List<LocationRaw>>

    @GET("data/2.5/weather")
    fun requestWeather(
        @Query("lat") lat: Double,
        @Query("lon") lng: Double,
    ): Single<WeatherRaw>

    @GET("data/2.5/forecast")
    fun requestWholeDayWeather(
        @Query("lat") lat: Double,
        @Query("lon") lng: Double,
    ): Single<WholeDayWeatherResponseRaw>
}