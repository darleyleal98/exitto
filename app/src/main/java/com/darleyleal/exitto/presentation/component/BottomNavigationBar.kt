package com.darleyleal.exitto.presentation.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.darleyleal.exitto.presentation.navigation.BottomNavigation
import com.darleyleal.exitto.presentation.navigation.bottomNavigationItems
import com.darleyleal.exitto.presentation.navigation.routes.HomeScreen

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentDestinationObject = remember(currentRoute) { bottomNavigationItems.find { it.route == currentRoute}}

    var selectedDestination by rememberSaveable { mutableStateOf(currentDestinationObject ?: HomeScreen) }

    NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
        bottomNavigationItems.forEach { item: BottomNavigation ->
            NavigationBarItem(
                selected = selectedDestination == item.route,
                onClick = {
                    navController.navigate(route = item.route)
                    selectedDestination = item.route
                },
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = item.label
                    )
                },
                label = {
                    item.label
                }
            )
        }
    }
}