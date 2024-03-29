package xyz.spacer.weatherforecast.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import xyz.spacer.weatherforecast.components.TempContent
import xyz.spacer.weatherforecast.components.TodayContent
import xyz.spacer.weatherforecast.data.DataOrException
import xyz.spacer.weatherforecast.model.Weather
import xyz.spacer.weatherforecast.navigation.WeatherScreens
import xyz.spacer.weatherforecast.screens.settings.SettingsViewModel
import xyz.spacer.weatherforecast.widgets.WeatherAppBar
import xyz.spacer.weatherforecast.widgets.weatherDetailRow

@Composable
fun MainScreen(
    navController: NavHostController,
    mainViewModel: MainViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    city: String?
) {
    val unitFromDb = settingsViewModel.unitList.collectAsState().value
    var unit by remember {
        mutableStateOf("metric")
    }
    var isMetric by remember {
        mutableStateOf(false)
    }

    if (!unitFromDb.isEmpty()) {
        unit = unitFromDb[0].unit.split(" ")[0].lowercase()
        isMetric = unit == "metric"

        val weatherData =
            produceState<DataOrException<Weather, Boolean, Exception>>(
                initialValue = DataOrException(
                    loading = true
                )
            ) {
                value = mainViewModel.getWeatherData(city = city.toString())
            }.value

        if (weatherData.loading == true) {
            CircularProgressIndicator()
        } else if (weatherData.data != null) {
            MainScaffold(weather = weatherData.data!!, navController, unit = unit)
        }
    }


}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScaffold(weather: Weather, navController: NavHostController, unit: String) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = weather.results.city,
            navController = navController,
            onAddActionClicked = {
                navController.navigate(WeatherScreens.SearchScreen.name)
            }
        ) {
            Log.d("BUTTON", "MainScaffold: ButtonClicked")
        }
    }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TempContent(data = weather, unit = unit)
            TodayContent(data = weather)
            Divider(
                modifier = Modifier
                    .height(2.dp)
            )
            Text(
                text = "Nesta Semana",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold
            )

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                color = Color(0xFFEEF1EF),
                shape = RoundedCornerShape(size = 14.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.padding(2.dp),
                    contentPadding = PaddingValues(1.dp)
                ) {

                    items(items = weather.results.forecast) { item ->
                        weatherDetailRow(weather = item)
                    }
                }
            }
        }

    }

}




