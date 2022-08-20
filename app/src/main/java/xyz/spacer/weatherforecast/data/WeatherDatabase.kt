package xyz.spacer.weatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import xyz.spacer.weatherforecast.model.Favorite
import xyz.spacer.weatherforecast.model.Unit

@Database(entities = [Favorite::class, Unit::class], version = 2, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}