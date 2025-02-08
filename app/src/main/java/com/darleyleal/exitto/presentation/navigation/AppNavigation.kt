package com.darleyleal.exitto.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.darleyleal.exitto.presentation.navigation.routes.AppRoutes
import com.darleyleal.exitto.presentation.screens.LoginScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    navController: NavHostController
) {
    NavHost(startDestination = AppRoutes.LoginScreen.name, navController = navController) {
        composable(route = AppRoutes.LoginScreen.name) {
            LoginScreen()
        }
    }
}