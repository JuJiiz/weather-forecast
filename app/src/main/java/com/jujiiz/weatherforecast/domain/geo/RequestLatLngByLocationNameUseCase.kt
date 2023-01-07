package com.jujiiz.weatherforecast.domain.geo

import com.jujiiz.weatherforecast.core.usecase.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class RequestLatLngByLocationNameUseCase @Inject constructor(
    private val geoRepository: GeoRepository,
) : SingleUseCase<String, LatLngEntity>() {

    override fun buildUseCase(input: String): Single<LatLngEntity> {
        return geoRepository.requestLocationByName(input)
    }
}