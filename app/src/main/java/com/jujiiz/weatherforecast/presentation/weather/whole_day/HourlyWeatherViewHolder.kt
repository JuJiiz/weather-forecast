package com.jujiiz.weatherforecast.presentation.weather.whole_day

import androidx.recyclerview.widget.RecyclerView
import com.jujiiz.weatherforecast.databinding.ItemHourlyWeatherBinding
import com.jujiiz.weatherforecast.domain.weather.TemperatureUnit
import com.jujiiz.weatherforecast.presentation.weather.WeatherViewEntity

class HourlyWeatherViewHolder(
    private val binding: ItemHourlyWeatherBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: WeatherViewEntity) {
        binding.apply {
            tvDate.text = item.date

            tvTime.text = item.time

            tvTemp.text = String.format("%.01f", item.temperature)

            tvTempUnit.text = when (item.unit) {
                TemperatureUnit.Celsius -> "°C"
                TemperatureUnit.Fahrenheit -> "°F"
            }
        }
    }
}