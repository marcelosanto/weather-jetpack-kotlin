package xyz.spacer.weatherforecast.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import xyz.spacer.weatherforecast.model.Favorite


@Dao
interface WeatherDao {

    @Query(value = "SELECT * from fav_tbl")
    fun getFavorites(): Flow<List<Favorite>>

    @Query(value = "SELECT * from fav_tbl where city =:city")
    suspend fun getFavById(city: String): Favorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favorite: Favorite)

    @Query("DELETE from fav_tbl")
    suspend fun deleteAllFavorites()

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)
}