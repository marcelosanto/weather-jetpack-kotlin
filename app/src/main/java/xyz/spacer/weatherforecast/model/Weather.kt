package xyz.spacer.weatherforecast.model

data class Weather(
    val `by`: String,
    val execution_time: Int,
    val from_cache: Boolean,
    val weatherObject: WeatherObject,
    val valid_key: Boolean
)