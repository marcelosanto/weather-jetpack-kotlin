package xyz.spacer.weatherforecast.repository

import android.util.Log
import xyz.spacer.weatherforecast.data.DataOrException
import xyz.spacer.weatherforecast.model.Weather
import xyz.spacer.weatherforecast.network.WeatherAPI
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherAPI) {
    suspend fun getWeather(cityQuery: String): DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(city_name = cityQuery)
        } catch (e: Exception) {

            return DataOrException(e = e)
        }

        Log.d("RES", "getWeather: $response")
        return DataOrException(data = response)
    }
}