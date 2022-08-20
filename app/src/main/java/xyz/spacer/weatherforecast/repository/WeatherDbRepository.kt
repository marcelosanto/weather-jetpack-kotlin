package xyz.spacer.weatherforecast.repository

import kotlinx.coroutines.flow.Flow
import xyz.spacer.weatherforecast.data.WeatherDao
import xyz.spacer.weatherforecast.model.Favorite
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao) {

    fun getFavorites(): Flow<List<Favorite>> = weatherDao.getFavorites()

    suspend fun getFavById(city: String): Favorite = weatherDao.getFavById(city)

    suspend fun insertFavorite(favorite: Favorite) = weatherDao.insertFavorite(favorite)

    suspend fun updateFavorite(favorite: Favorite) = weatherDao.updateFavorite(favorite)

    suspend fun deleteAllFavorites() = weatherDao.deleteAllFavorites()

    suspend fun deleteFavorite(favorite: Favorite) = weatherDao.deleteFavorite(favorite)
}