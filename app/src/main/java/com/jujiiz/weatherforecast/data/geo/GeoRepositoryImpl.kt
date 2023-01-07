package com.jujiiz.weatherforecast.data.geo

import com.jujiiz.weatherforecast.core.network.ApiService
import com.jujiiz.weatherforecast.domain.geo.GeoRepository
import com.jujiiz.weatherforecast.domain.geo.LatLngEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GeoRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val latLngStore: LatLngStore,
) : GeoRepository {

    override fun setSelectedLocation(latLng: LatLngEntity): Completable {
        latLngStore.value = latLng
        return Completable.complete()
    }

    override fun retrieveSelectedLocation(): Flowable<LatLngEntity> {
        return latLngStore.retrieve()
    }

    override fun requestLocationByName(query: String): Single<LatLngEntity> {
        return apiService.requestLocationByName(query)
            .map {
                val first = it.firstOrNull()
                if (first != null) {
                    LatLngEntity(
                        latitude = first.latitude,
                        longitude = first.longitude,
                    )
                } else {
                    throw Exception("No location found")
                }
            }
            .doOnSuccess {
                latLngStore.value = it
            }
    }
}