package com.jujiiz.weatherforecast.presentation.weather.current

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import com.jujiiz.weatherforecast.R
import com.jujiiz.weatherforecast.core.BaseActivity
import com.jujiiz.weatherforecast.databinding.ActivityMainBinding
import com.jujiiz.weatherforecast.domain.geo.LatLngEntity
import com.jujiiz.weatherforecast.domain.weather.TemperatureUnit
import com.jujiiz.weatherforecast.presentation.dialog.TextInputDialog
import com.jujiiz.weatherforecast.presentation.weather.WeatherViewEntity
import com.jujiiz.weatherforecast.presentation.weather.whole_day.WholeDayWeatherActivity

class CurrentWeatherActivity : BaseActivity(), LocationListener {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: CurrentWeatherViewModel by viewModels { viewModelFactory }

    private val locationManager: LocationManager by lazy {
        getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViewProperties()
        initObservers()
    }

    private fun initViewProperties() {
        binding.apply {
            requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)

            btnSearchLocation.setOnClickListener {
                onSearchLocationClick()
            }

            btnWholeDay.setOnClickListener {
                viewWeatherOfWholeDay()
            }

            btnSetting.setOnClickListener {
                viewModel.switchTempUnit()
            }
        }
    }

    private fun onSearchLocationClick() {
        TextInputDialog.getInstance(
            context = this,
            onSubmit = viewModel::updateLocationByName,
        ).show()
    }

    private fun viewWeatherOfWholeDay() {
        val intent = Intent(this, WholeDayWeatherActivity::class.java)
        startActivity(intent)
    }

    private fun initObservers() {
        viewModel.liveWeather.observe(this, ::handleWeather)
    }

    private fun handleWeather(weather: WeatherViewEntity) {
        binding.apply {
            when (weather.unit) {
                TemperatureUnit.Celsius -> btnSetting.setImageResource(R.drawable.temperature_fahrenheit)
                TemperatureUnit.Fahrenheit -> btnSetting.setImageResource(R.drawable.temperature_celsius)
            }

            tvLocationName.text = weather.locationName

            tvCurrentTemp.text = String.format("%.01f", weather.temperature)
            tvFeelLike.text = String.format("%.01f", weather.feelsLike)

            val unitName = when (weather.unit) {
                TemperatureUnit.Celsius -> "°C"
                TemperatureUnit.Fahrenheit -> "°F"
            }
            tvTempUnit.text = unitName
            tvFeelLikeUnit.text = unitName

            tvHumid.text = weather.humidity.toString()
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            requestLocation()
        } else {
            Toast.makeText(
                this,
                "Need to use location permission to find weather near you.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun requestLocation() {

        if (ActivityCompat.checkSelfPermission(
                this,
                ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (hasGps) {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000,
                0F,
                this,
            )
        }
    }

    override fun onLocationChanged(location: Location) {
        val latLng = LatLngEntity(
            latitude = location.latitude,
            longitude = location.longitude,
        )
        viewModel.updateLocation(latLng)
        locationManager.removeUpdates(this)
    }
}