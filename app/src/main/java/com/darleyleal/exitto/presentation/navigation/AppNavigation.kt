package com.darleyleal.exitto.presentation.navigation

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.darleyleal.exitto.data.data_store.UserDataStore
import com.darleyleal.exitto.presentation.navigation.enums.AppRoutes
import com.darleyleal.exitto.presentation.screens.analytics.AnalyticsScreen
import com.darleyleal.exitto.presentation.screens.habits.HabitsScreen
import com.darleyleal.exitto.presentation.screens.home.HomeScreen
import com.darleyleal.exitto.presentation.screens.main.MainScreen
import com.darleyleal.exitto.presentation.screens.profile.ProfileScreen
import com.darleyleal.exitto.presentation.screens.start.StartScreen
import kotlinx.coroutines.flow.first

@Composable
fun AppNavigation(
    context: Context,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    navController: NavHostController
) {
    val store = UserDataStore(context)
    var startDestination by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        val showStartScreen = store.getAccessToken.first()

        startDestination = if (!showStartScreen) {
            AppRoutes.StartScreen.name
        } else {
            AppRoutes.MainScreen.name
        }
    }

    if (startDestination != null) {
        NavHost(
            startDestination = startDestination!!,
            navController = navController
        ) {
            composable(
                route = AppRoutes.StartScreen.name
            ) {
                StartScreen(onNavigationToMainScreen = {
                    navController.navigate(route = AppRoutes.MainScreen.name)
                })
            }
            composable(route = AppRoutes.MainScreen.name) {
                MainScreen()
            }
            composable(route = AppRoutes.HomeScreen.name) {
                HomeScreen(modifier = modifier, paddingValues = paddingValues)
            }
            composable(route = AppRoutes.HabitsScreen.name) {
                HabitsScreen(paddingValues = paddingValues)
            }
            composable(route = AppRoutes.AnalyticsScreen.name) {
                AnalyticsScreen(paddingValues = paddingValues)
            }
            composable(route = AppRoutes.ProfileScreen.name) {
                ProfileScreen(paddingValues = paddingValues)
            }
        }
    }
}