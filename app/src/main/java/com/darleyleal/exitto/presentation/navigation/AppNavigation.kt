package com.darleyleal.exitto.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.darleyleal.exitto.presentation.navigation.routes.AnalyticsScreen
import com.darleyleal.exitto.presentation.navigation.routes.HealthScreen
import com.darleyleal.exitto.presentation.navigation.routes.HomeScreen
import com.darleyleal.exitto.presentation.navigation.routes.MainScreen
import com.darleyleal.exitto.presentation.navigation.routes.ProfileScreen
import com.darleyleal.exitto.presentation.navigation.routes.TrainScreen
import com.darleyleal.exitto.presentation.screen.analytics.AnalyticsScreen
import com.darleyleal.exitto.presentation.screen.health.HealthScreen
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
            MainScreen(innerPadding = innerPadding, navController = navController)
        }
        composable<AnalyticsScreen> {
            AnalyticsScreen()
        }
        composable<HealthScreen> {
            HealthScreen()
        }
        composable<HomeScreen> {
            HomeScreen()
        }
        composable<ProfileScreen> {
            ProfileScreen()
        }
        composable<TrainScreen> {
            TrainScreen()
        }
    }
}