package com.jujiiz.weatherforecast.core.di.controller

import androidx.lifecycle.ViewModel
import com.jujiiz.weatherforecast.core.di.ActivityScoped
import com.jujiiz.weatherforecast.core.di.ViewModelKey
import com.jujiiz.weatherforecast.presentation.weather.whole_day.WholeDayWeatherActivity
import com.jujiiz.weatherforecast.presentation.weather.whole_day.WholeDayWeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class WholeDayWeatherModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun contributeWholeDayWeatherActivity(): WholeDayWeatherActivity

    @Binds
    @IntoMap
    @ViewModelKey(WholeDayWeatherViewModel::class)
    abstract fun bindWholeDayWeatherViewModel(viewModel: WholeDayWeatherViewModel): ViewModel
}