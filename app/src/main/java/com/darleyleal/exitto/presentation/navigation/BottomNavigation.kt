package com.darleyleal.exitto.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.darleyleal.exitto.presentation.navigation.routes.AnalyticsScreen
import com.darleyleal.exitto.presentation.navigation.routes.HealthScreen
import com.darleyleal.exitto.presentation.navigation.routes.HomeScreen
import com.darleyleal.exitto.presentation.navigation.routes.ProfileScreen
import com.darleyleal.exitto.presentation.navigation.routes.TrainScreen

data class BottomNavigation(
    val route: Any,
    val label: String,
    val icon: ImageVector,
)

val bottomNavigationItems = listOf(
    BottomNavigation(
        route = HomeScreen,
        label = "Home",
        icon = Icons.Default.Home
    ),
    BottomNavigation(
        route = HealthScreen,
        label = "Health",
        icon = Icons.Default.HealthAndSafety
    ),
    BottomNavigation(
        route = AnalyticsScreen,
        label = "Analytics",
        icon = Icons.Filled.BarChart
    ),
    BottomNavigation(
        route = TrainScreen,
        label = "Train",
        icon = Icons.Filled.FitnessCenter
    ),
    BottomNavigation(
        route = ProfileScreen,
        label = "Profile",
        icon = Icons.Default.Person
    ),
)
