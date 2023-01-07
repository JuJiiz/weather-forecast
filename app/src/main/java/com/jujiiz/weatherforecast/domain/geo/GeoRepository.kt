package com.jujiiz.weatherforecast.domain.geo

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface GeoRepository {
    fun setSelectedLocation(latLng: LatLngEntity): Completable

    fun retrieveSelectedLocation(): Flowable<LatLngEntity>

    fun requestLocationByName(query: String): Single<LatLngEntity>
}