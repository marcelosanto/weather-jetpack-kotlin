package xyz.spacer.weatherforecast.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xyz.spacer.weatherforecast.R
import xyz.spacer.weatherforecast.model.Weather
import xyz.spacer.weatherforecast.utils.Constants.getIconsWeather

@Composable
fun TempContent(data: Weather, unit: String) {

    Column(
        Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        Image(
            painterResource(id = getIconsWeather[data.results.condition_slug]!!),
            contentDescription = ""
        )

        val temp = if (unit == "metric") data.results.temp else {
            (data.results.temp * 1.8) + 32
        }

        Text(
            text = "${temp}º",
            color = Color.DarkGray,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = data.results.description,
            fontWeight = FontWeight.SemiBold,
            color = Color.DarkGray,
            fontSize = 20.sp
        )
        Spacer(Modifier.height(20.0.dp))

        CardTempInfo(data)
    }
}

@Composable
private fun CardTempInfo(data: Weather) {
    Card(
        Modifier.padding(10.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(24.dp),
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,

            ) {

            ColumnInfo("${data.results.humidity}%", R.drawable.humidity)

            ColumnInfo(data.results.wind_speedy, R.drawable.wind)


        }
    }
}

@Composable
private fun DividerRows() {
    TabRowDefaults.Divider(
        color = Color.Gray,
        modifier = Modifier
            .height(30.dp)
            .width(1.dp)
            .alpha(0.5f)
    )
}

@Composable
private fun ColumnInfo(info: String, icon: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Image(
            painterResource(id = icon),
            contentDescription = "today image",
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .width(20.dp)
                .height(20.dp),
        )
        Text(
            text = info,
            fontSize = 18.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.SemiBold
        )
    }
}
