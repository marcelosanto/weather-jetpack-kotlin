package xyz.spacer.weatherforecast.screens.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import xyz.spacer.weatherforecast.R
import xyz.spacer.weatherforecast.components.TempContent
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
            value = mainViewModel.getWeatherData(city = "Vitoria,ES")
        }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        MainScaffold(weather = weatherData.data!!, navController)
    }
}

@Composable
fun MainScaffold(weather: Weather, navController: NavHostController) {
    Scaffold(topBar = {
        WeatherAppBar(
            title = weather.results.city,
            navController = navController,
        ) {
            Log.d("BUTTON", "MainScaffold: ButtonClicked")
        }
    }
    ) {
        Column() {
            TempContent(data = weather)
            TodayContent(data = weather)
        }

    }

}


@Composable
fun TodayContent(data: Weather) {
    Text(text = "Today", Modifier.padding(start = 20.dp, bottom = 10.dp))

    Row(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(start = 20.dp, end = 20.dp, bottom = 0.dp)
            .background(Color.Red),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        TodayInfo(vertical = Arrangement.Center)
        TodayInfo(vertical = Arrangement.Top)
        TodayInfo(vertical = Arrangement.Bottom)


    }

}

@Composable
private fun TodayInfo(vertical: Arrangement.Vertical) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(80.dp)
            .background(Color.Blue),
        verticalArrangement = vertical,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(text = "12 PM", fontSize = 12.sp)
        Image(
            painterResource(id = R.drawable.sun),
            contentDescription = "today image",
            Modifier
                .width(40.dp)
                .height(40.dp),
        )
        Text(text = "18º")
    }
}