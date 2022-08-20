package xyz.spacer.weatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import xyz.spacer.weatherforecast.model.Favorite

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}