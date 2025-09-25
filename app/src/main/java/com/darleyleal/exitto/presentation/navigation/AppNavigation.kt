package com.darleyleal.exitto.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.darleyleal.exitto.presentation.screens.analytics.AnalyticsScreen
import com.darleyleal.exitto.presentation.screens.gym.GymScreen
import com.darleyleal.exitto.presentation.screens.health.HealthScreen
import com.darleyleal.exitto.presentation.screens.home.HomeScreen
import com.darleyleal.exitto.presentation.screens.main.MainScreen
import com.darleyleal.exitto.presentation.screens.profile.ProfileScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(startDestination = Routes.Main.name, navController = navController) {
        composable(route = Routes.Main.name) {
            MainScreen(modifier, navController)
        }
        composable(route = Routes.Home.name) {
            HomeScreen(modifier)
        }
        composable(route = Routes.Health.name) {
            HealthScreen(modifier)
        }
        composable(route = Routes.Analytics.name) {
            AnalyticsScreen(modifier)
        }
        composable(route = Routes.Gym.name) {
            GymScreen(modifier)
        }
        composable(route = Routes.Profile.name) {
            ProfileScreen(modifier)
        }
    }
}