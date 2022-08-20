package xyz.spacer.weatherforecast.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import xyz.spacer.weatherforecast.model.Favorite
import xyz.spacer.weatherforecast.navigation.WeatherScreens
import xyz.spacer.weatherforecast.screens.favorite.FavoriteViewModel

//@Preview
@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {
    val showDialog = remember {
        mutableStateOf(false)
    }

    if (showDialog.value) {
        ShowSettingDropDownMenu(showDialog = showDialog, navController = navController)
    }

    TopAppBar(
        title = {
            Text(
                text = title, color = MaterialTheme.colors.onSecondary,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp)
            )
        },
        actions = {
            if (isMainScreen) {
                IconButton(onClick = { onAddActionClicked.invoke() }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "search icon")
                }
                IconButton(onClick = { showDialog.value = true }) {
                    Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "more icon")
                }
            } else Box {}
        },
        navigationIcon = {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSecondary,
                    modifier = Modifier.clickable {
                        onButtonClicked.invoke()
                    }
                )
            }

            if (isMainScreen) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite icon",
                    modifier = Modifier
                        .scale(0.9f)
                        .clickable {
                            val (city, country) = title.split(",")

                            favoriteViewModel
                                .insertFavorite(
                                    Favorite(
                                        city = city,
                                        country = country
                                    )
                                )
                        },
                    tint = Color.Red.copy(alpha = 0.6f)
                )
            }
        },
        backgroundColor = Color.Transparent,
        elevation = elevation
    )
}

@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean>, navController: NavController) {
    var expanded by remember {
        mutableStateOf(true)
    }
    val items = listOf("Favorites", "Settings", "About")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(140.dp)
                .background(
                    Color.White
                )
        ) {
            items.forEachIndexed { index, text ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    showDialog.value = false
                }) {
                    Icon(
                        imageVector = when (text) {
                            "Favorites" -> Icons.Default.FavoriteBorder
                            "Settings" -> Icons.Default.Settings
                            else -> Icons.Default.Info

                        }, contentDescription = null,
                        tint = Color.LightGray
                    )
                    Text(
                        text = text,
                        modifier = Modifier.clickable {
                            navController.navigate(
                                when (text) {
                                    "Favorites" -> WeatherScreens.FavoriteScreen.name
                                    "Settings" -> WeatherScreens.SettingsScreen.name
                                    else -> WeatherScreens.AboutScreen.name

                                }
                            )
                        },
                        fontWeight = FontWeight.W300
                    )
                }
            }
        }
    }
}
