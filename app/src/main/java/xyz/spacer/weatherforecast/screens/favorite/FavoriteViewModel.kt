package xyz.spacer.weatherforecast.screens.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import xyz.spacer.weatherforecast.model.Favorite
import xyz.spacer.weatherforecast.repository.WeatherDbRepository
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: WeatherDbRepository) :
    ViewModel() {
    private val _favList = MutableStateFlow<List<Favorite>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavorites().distinctUntilChanged().collect { listOfFavs ->
                if (listOfFavs.isEmpty()) {
                    Log.d("FAV", ": Empty favs")
                } else {
                    _favList.value = listOfFavs
                    Log.d("FAV", ": ${favList.value}")
                }
            }
        }
    }

    fun insertFavorite(favorite: Favorite) = viewModelScope.launch {
        repository.insertFavorite(favorite)
    }

    fun updateFavorite(favorite: Favorite) = viewModelScope.launch {
        repository.updateFavorite(favorite)
    }

    fun removeFavorite(favorite: Favorite) = viewModelScope.launch {
        repository.deleteFavorite(favorite)
    }

}