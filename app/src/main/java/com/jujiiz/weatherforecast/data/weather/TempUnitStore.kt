package com.jujiiz.weatherforecast.data.weather

import com.jujiiz.weatherforecast.core.BaseReactiveStore
import com.jujiiz.weatherforecast.domain.weather.TemperatureUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TempUnitStore @Inject constructor() : BaseReactiveStore<TemperatureUnit>() {
    init {
        value = TemperatureUnit.Celsius
    }
}