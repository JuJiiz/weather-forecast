package com.jujiiz.weatherforecast.presentation.weather.whole_day

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jujiiz.weatherforecast.core.BaseActivity
import com.jujiiz.weatherforecast.databinding.ActivityWholeDayWeatherBinding

class WholeDayWeatherActivity : BaseActivity() {

    private val binding: ActivityWholeDayWeatherBinding by lazy {
        ActivityWholeDayWeatherBinding.inflate(layoutInflater)
    }

    private val viewModel: WholeDayWeatherViewModel by viewModels { viewModelFactory }

    private val hourlyWeatherAdapter: HourlyWeatherAdapter = HourlyWeatherAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViewProperties()
        initObservers()
    }

    private fun initViewProperties() {
        binding.apply {
            btnBack.setOnClickListener { finish() }

            rvHourlyWeather.apply {
                layoutManager = LinearLayoutManager(this@WholeDayWeatherActivity)
                adapter = hourlyWeatherAdapter
            }
        }
    }

    private fun initObservers() {
        viewModel.liveWeather.observe(this, ::handleWholeDayWeather)
    }

    private fun handleWholeDayWeather(day: WholeDayWeatherViewEntity) {
        binding.apply {
            tvLocationName.text = day.city.name

            hourlyWeatherAdapter.setItems(day.weathers)
        }
    }
}