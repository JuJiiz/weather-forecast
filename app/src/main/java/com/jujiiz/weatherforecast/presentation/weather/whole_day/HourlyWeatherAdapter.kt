package com.jujiiz.weatherforecast.presentation.weather.whole_day

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jujiiz.weatherforecast.databinding.ItemHourlyWeatherBinding
import com.jujiiz.weatherforecast.presentation.weather.WeatherViewEntity

class HourlyWeatherAdapter : RecyclerView.Adapter<HourlyWeatherViewHolder>() {
    private var items: List<WeatherViewEntity> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<WeatherViewEntity>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyWeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHourlyWeatherBinding.inflate(layoutInflater, parent, false)
        return HourlyWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HourlyWeatherViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}