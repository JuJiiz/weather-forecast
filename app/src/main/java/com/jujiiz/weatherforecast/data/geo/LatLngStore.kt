package com.jujiiz.weatherforecast.data.geo

import com.jujiiz.weatherforecast.core.BaseReactiveStore
import com.jujiiz.weatherforecast.domain.geo.LatLngEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LatLngStore @Inject constructor() : BaseReactiveStore<LatLngEntity>()