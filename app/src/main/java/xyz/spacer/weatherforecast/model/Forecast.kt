package xyz.spacer.weatherforecast.model

data class Forecast(
    val condition: String,
    val date: String,
    val description: String,
    val max: Int,
    val min: Int,
    val weekday: String
)