package com.jujiiz.weatherforecast.core.di

import com.jujiiz.weatherforecast.core.di.controller.CurrentWeatherModule
import com.jujiiz.weatherforecast.core.di.controller.WholeDayWeatherModule
import dagger.Module

@Module(
    includes = [
        CurrentWeatherModule::class,
        WholeDayWeatherModule::class,
    ]
)
abstract class ControllerModule