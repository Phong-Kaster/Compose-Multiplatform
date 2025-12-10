package org.astronex.olyn.domain.enums

enum class TemperatureUnit (
    val signal: String = "°C"
) {
    Celsius(signal = "°C"),
    Fahrenheit(signal = "°F");

    companion object {
        fun celsiusToFahrenheit(temperatureValue: Float): Float {
            return (temperatureValue * 1.8f) + 32f
        }

        fun fahrenheitToCelsius(temperatureValue: Float): Float {
            return (temperatureValue - 32f) / 1.8f
        }
    }
}