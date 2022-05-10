package xyz.spacer.weatherforecast.model

data class Weather(
    val `by`: String,
    val execution_time: Int,
    val from_cache: Boolean,
    val results: Results,
    val valid_key: Boolean
)