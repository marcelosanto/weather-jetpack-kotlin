package xyz.spacer.weatherforecast.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import xyz.spacer.weatherforecast.components.TempContent
import xyz.spacer.weatherforecast.components.TodayContent
import xyz.spacer.weatherforecast.data.DataOrException
import xyz.spacer.weatherforecast.model.Forecast
import xyz.spacer.weatherforecast.model.Weather
import xyz.spacer.weatherforecast.utils.Constants
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TempContent(data = weather)
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

@Composable
fun weatherDetailRow(weather: Forecast) {
    Surface(
        Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = weather.weekday, modifier = Modifier.padding(start = 5.dp))
            Image(
                painterResource(Constants.getIconsWeather[weather.condition]!!),
                contentDescription = "weekday icon",
                Modifier
                    .width(40.dp)
                    .height(40.dp)
            )
            Text(text = weather.description)
            Text(text = "${weather.max}°")
            Text(text = "${weather.min}°")
        }
    }
}


