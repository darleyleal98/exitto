package com.darleyleal.exitto.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.darleyleal.exitto.presentation.navigation.routes.AnalyticsScreen
import com.darleyleal.exitto.presentation.navigation.routes.HomeScreen
import com.darleyleal.exitto.presentation.navigation.routes.MainScreen
import com.darleyleal.exitto.presentation.navigation.routes.ProfileScreen
import com.darleyleal.exitto.presentation.navigation.routes.TrainScreen
import com.darleyleal.exitto.presentation.screen.analytics.AnalyticsScreen
import com.darleyleal.exitto.presentation.screen.home.HomeScreen
import com.darleyleal.exitto.presentation.screen.main.MainScreen
import com.darleyleal.exitto.presentation.screen.profile.ProfileScreen
import com.darleyleal.exitto.presentation.screen.train.TrainScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        startDestination = MainScreen, navController = navController
    ) {
        composable<MainScreen> {
            MainScreen()
        }
        composable<AnalyticsScreen> {
            AnalyticsScreen()
        }
        composable<HomeScreen> {
            HomeScreen()
        }
        composable<MainScreen> {
            MainScreen()
        }
        composable<ProfileScreen> {
            ProfileScreen()
        }
        composable<TrainScreen> {
            TrainScreen()
        }
    }
}