package com.darleyleal.exitto.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

val bottomNavigationList = listOf(
    NavigationItem.Home,
    NavigationItem.Health,
    NavigationItem.Analytics,
    NavigationItem.Gym,
    NavigationItem.Profile
)

sealed class NavigationItem(val name: String, val icon: ImageVector, val label: String) {
    object Home : NavigationItem(
        name = Routes.Home.name,
        icon = Icons.Default.Home,
        label = Routes.Home.name
    )

    object Health : NavigationItem(
        name = Routes.Health.name,
        icon = Icons.Default.Favorite,
        label = Routes.Health.name
    )

    object Analytics : NavigationItem(
        name = Routes.Analytics.name,
        icon = Icons.Default.BarChart,
        label = Routes.Analytics.name
    )

    object Gym : NavigationItem(
        name = Routes.Gym.name,
        icon = Icons.Default.FitnessCenter,
        label = Routes.Gym.name
    )

    object Profile : NavigationItem(
        name = Routes.Profile.name,
        icon = Icons.Default.Person,
        label = Routes.Profile.name
    )
}