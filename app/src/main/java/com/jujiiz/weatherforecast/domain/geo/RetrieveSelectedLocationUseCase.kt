package com.jujiiz.weatherforecast.domain.geo

import com.jujiiz.weatherforecast.core.usecase.FlowableUseCase
import io.reactivex.Flowable
import javax.inject.Inject

class RetrieveSelectedLocationUseCase @Inject constructor(
    private val geoRepository: GeoRepository,
) : FlowableUseCase<LatLngEntity>() {

    override fun buildUseCase(): Flowable<LatLngEntity> {
        return geoRepository.retrieveSelectedLocation()
    }
}