package xyz.spacer.weatherforecast.network

import retrofit2.http.GET
import retrofit2.http.Query
import xyz.spacer.weatherforecast.model.Weather
import xyz.spacer.weatherforecast.utils.Constants
import javax.inject.Singleton

@Singleton
interface WeatherAPI {
    @GET(value = "weather")
    suspend fun getWeather(
        @Query("key") key: String = Constants.API_KEY,
        @Query("city_name") city_name: String
    ): Weather
}