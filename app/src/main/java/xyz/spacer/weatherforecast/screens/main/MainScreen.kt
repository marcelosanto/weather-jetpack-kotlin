package xyz.spacer.weatherforecast.screens.main

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import xyz.spacer.weatherforecast.data.DataOrException
import xyz.spacer.weatherforecast.model.Weather
import xyz.spacer.weatherforecast.widgets.WeatherAppBar

@Composable
fun MainScreen(navController: NavHostController, mainViewModel: MainViewModel = hiltViewModel()) {


    val weatherData =
        produceState<DataOrException<Weather, Boolean, Exception>>(
            initialValue = DataOrException(
                loading = true
            )
        ) {
            value = mainViewModel.getWeatherData(city = "Vitória")
        }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        MainScaffold(weather = weatherData.data!!, navController)
    }
}

@Composable
fun MainScaffold(weather: Weather, navController: NavHostController) {
    Scaffold(topBar = { WeatherAppBar(title = "Vitoria/ES") }) {
        MainContent(data = weather)
    }

}

@Composable
fun MainContent(data: Weather) {
    Text(text = data.results.city)
}
