package com.darleyleal.exitto.presentation.navigation.bottom_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.darleyleal.exitto.presentation.navigation.enums.AppRoutes
import com.darleyleal.exitto.presentation.navigation.enums.BottomNavigationRoute

data class BottomNavItem(val route: String, val icon: ImageVector, val label: String)

val bottomNavigationItems = listOf(
    BottomNavItem(
        route = AppRoutes.HomeScreen.name,
        icon = Icons.Default.Home,
        label = BottomNavigationRoute.Home.name
    ),
    BottomNavItem(
        route = AppRoutes.HabitsScreen.name,
        icon = Icons.Filled.Favorite,
        label = BottomNavigationRoute.Habits.name
    ),
    BottomNavItem(
        route = AppRoutes.AnalyticsScreen.name,
        icon = Icons.Filled.BarChart,
        label = BottomNavigationRoute.Analytics.name
    ),
    BottomNavItem(
        route = AppRoutes.ProfileScreen.name,
        icon = Icons.Default.Person,
        label = BottomNavigationRoute.Profile.name
    )
)