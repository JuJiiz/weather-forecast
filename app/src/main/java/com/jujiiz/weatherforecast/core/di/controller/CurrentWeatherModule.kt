package com.jujiiz.weatherforecast.core.di.controller

import androidx.lifecycle.ViewModel
import com.jujiiz.weatherforecast.presentation.weather.current.CurrentWeatherActivity
import com.jujiiz.weatherforecast.presentation.weather.current.CurrentWeatherViewModel
import com.jujiiz.weatherforecast.core.di.ActivityScoped
import com.jujiiz.weatherforecast.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class CurrentWeatherModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun contributeCurrentActivity(): CurrentWeatherActivity

    @Binds
    @IntoMap
    @ViewModelKey(CurrentWeatherViewModel::class)
    abstract fun bindCurrentViewModel(viewModel: CurrentWeatherViewModel): ViewModel
}