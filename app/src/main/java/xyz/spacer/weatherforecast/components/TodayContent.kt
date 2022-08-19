package xyz.spacer.weatherforecast.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import xyz.spacer.weatherforecast.R
import xyz.spacer.weatherforecast.model.Weather

@Composable
fun TodayContent(data: Weather) {
    Text(text = "Today", Modifier.padding(start = 20.dp, bottom = 10.dp))

    Row(
        Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        TodayInfo(vertical = Arrangement.Center, R.drawable.sunrise, data.results.sunrise)
        TodayInfo(vertical = Arrangement.Bottom, R.drawable.sunset, data.results.sunset)


    }

}

@Composable
private fun TodayInfo(vertical: Arrangement.Vertical, icon: Int, hour: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(
            painterResource(id = icon),
            contentDescription = "today image",
            Modifier
                .width(30.dp)
                .height(30.dp),
        )
        Text(text = hour)
    }
}