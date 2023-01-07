package com.jujiiz.weatherforecast.domain.geo

import com.jujiiz.weatherforecast.core.usecase.CompletableUseCase
import io.reactivex.Completable
import javax.inject.Inject

class RequestSetSelectedLocationUseCase @Inject constructor(
    private val geoRepository: GeoRepository,
) : CompletableUseCase<LatLngEntity>() {

    override fun buildUseCase(input: LatLngEntity): Completable {
        return geoRepository.setSelectedLocation(input)
    }
}