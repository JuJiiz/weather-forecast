package com.jujiiz.weatherforecast

import com.jujiiz.weatherforecast.domain.weather.TemperatureUnit
import org.junit.Test

import org.junit.Assert.*

class ConvertTemperatureUnitTest {
    @Test
    fun testConvertKelvinToCelsius() {
        val kelvin = 299.85
        val celsius = TemperatureUnit.convertKelvinToCelsius(kelvin)
        assertEquals(26.70, celsius,0.1)
    }

    @Test
    fun testConvertKelvinToFahrenheit() {
        val kelvin = 299.85
        val fahrenheit = TemperatureUnit.convertKelvinToFahrenheit(kelvin)
        assertEquals(80.06, fahrenheit,0.1)
    }
}