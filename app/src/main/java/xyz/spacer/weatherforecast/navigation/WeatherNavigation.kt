package xyz.spacer.weatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import xyz.spacer.weatherforecast.screens.main.MainScreen
import xyz.spacer.weatherforecast.screens.main.MainViewModel
import xyz.spacer.weatherforecast.screens.search.SearchScreen
import xyz.spacer.weatherforecast.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreen.name
    ) {
        composable(WeatherScreens.SplashScreen.name) {
            WeatherSplashScreen(navController = navController)
        }


        val route = WeatherScreens.MainScreen.name
        composable("$route/{city}", arguments = listOf(
            navArgument(name = "city") {
                type = NavType.StringType
            }
        )) { navBack ->
            navBack.arguments?.getString("city").let { city ->

                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController = navController, mainViewModel, city = city)
            }
        }

        composable(WeatherScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }
    }
}

