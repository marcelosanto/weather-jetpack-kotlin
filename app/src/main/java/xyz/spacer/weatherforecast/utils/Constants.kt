package xyz.spacer.weatherforecast.utils

import xyz.spacer.weatherforecast.R

object Constants {
    const val BASE_URL = "https://api.hgbrasil.com/"
    const val API_KEY = "0e118d1233"

    val iconsMaps = mapOf(
        "storm" to R.drawable.storm,
        "snow" to R.drawable.snow,
        "hail" to R.drawable.hail,
        "rain" to R.drawable.rain,
        "fog" to R.drawable.fog,
        "clear_day" to R.drawable.clear_day,
        "clear_night" to R.drawable.clear_night,
        "cloud" to R.drawable.cloud,
        "cloudly_day" to R.drawable.cloudly_day,
        "cloudly_night" to R.drawable.cloudly_night,
        "none_day" to R.drawable.none_day,
        "none_night" to R.drawable.none_night
    )
}