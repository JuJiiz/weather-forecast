package com.jujiiz.weatherforecast.core

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.jujiiz.weatherforecast.core.di.AppComponent
import com.jujiiz.weatherforecast.core.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class WeatherForecastApp :Application(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)

        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        appComponent.inject(this)
    }
}