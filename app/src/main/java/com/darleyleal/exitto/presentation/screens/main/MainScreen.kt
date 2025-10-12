package com.darleyleal.exitto.presentation.screens.main

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.darleyleal.exitto.presentation.navigation.bottomNavigationList
import com.darleyleal.exitto.presentation.provider.ViewModelProvider
import com.darleyleal.exitto.presentation.screens.analytics.AnalyticsScreen
import com.darleyleal.exitto.presentation.screens.gym.GymScreen
import com.darleyleal.exitto.presentation.screens.health.HealthScreen
import com.darleyleal.exitto.presentation.screens.home.HomeScreen
import com.darleyleal.exitto.presentation.screens.profile.ProfileScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(modifier: Modifier = Modifier, navController: NavHostController, viewModelProvider: ViewModelProvider) {
    var selected by rememberSaveable { mutableIntStateOf(0) }

    val systemUiController = rememberSystemUiController()

    LaunchedEffect(Unit) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false
        )
    }

    Scaffold(
        content = {
            when (selected) {
                0 -> HomeScreen(modifier, viewModelProvider = viewModelProvider)
                1 -> HealthScreen(modifier, viewModelProvider = viewModelProvider)
                2 -> AnalyticsScreen(modifier, viewModelProvider = viewModelProvider)
                3 -> GymScreen(modifier, viewModelProvider = viewModelProvider)
                4 -> ProfileScreen(modifier, viewModelProvider = viewModelProvider)
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.Transparent,
            ) {
                bottomNavigationList.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selected == index,
                        onClick = {
                            selected = index
                        },
                        icon = {
                            Icon(imageVector = item.icon, contentDescription = item.label)
                        },
                        label = {
                            Text(item.label)
                        }
                    )
                }
            }
        }
    )
}