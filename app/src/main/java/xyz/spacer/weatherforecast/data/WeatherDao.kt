package xyz.spacer.weatherforecast.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import xyz.spacer.weatherforecast.model.Favorite
import xyz.spacer.weatherforecast.model.Unit


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

    // Unit table
    @Query(value = "SELECT * from settings_tbl")
    fun getUnits(): Flow<List<Unit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unit: Unit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(unit: Unit)

    @Query(value = "DELETE from settings_tbl")
    suspend fun deleteAllUnits()

    @Delete
    suspend fun deleteUnit(unit: Unit)
}