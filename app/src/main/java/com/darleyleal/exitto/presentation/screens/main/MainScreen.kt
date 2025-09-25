package com.darleyleal.exitto.presentation.screens.main

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.darleyleal.exitto.presentation.navigation.bottomNavigationList
import com.darleyleal.exitto.presentation.screens.analytics.AnalyticsScreen
import com.darleyleal.exitto.presentation.screens.gym.GymScreen
import com.darleyleal.exitto.presentation.screens.health.HealthScreen
import com.darleyleal.exitto.presentation.screens.home.HomeScreen
import com.darleyleal.exitto.presentation.screens.profile.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    var selected by rememberSaveable { mutableIntStateOf(0) }
    Scaffold(
        modifier = modifier,
        topBar = {

        },
        content = {
            when (selected) {
                0 -> HomeScreen(modifier)
                1 -> HealthScreen(modifier)
                2 -> AnalyticsScreen(modifier)
                3 -> GymScreen(modifier)
                4 -> ProfileScreen(modifier)
            }
        },
        bottomBar = {
            NavigationBar {
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