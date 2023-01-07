package com.jujiiz.weatherforecast.core.di

import com.jujiiz.weatherforecast.data.geo.GeoRepositoryImpl
import com.jujiiz.weatherforecast.data.weather.WeatherRepositoryImpl
import com.jujiiz.weatherforecast.domain.geo.GeoRepository
import com.jujiiz.weatherforecast.domain.weather.WeatherRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

    @Binds
    abstract fun bindGeoRepository(impl: GeoRepositoryImpl): GeoRepository
}