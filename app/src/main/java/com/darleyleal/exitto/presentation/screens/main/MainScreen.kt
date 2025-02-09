package com.darleyleal.exitto.presentation.screens.main

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.darleyleal.exitto.presentation.navigation.bottom_navigation.BottomNavigationBar
import com.darleyleal.exitto.presentation.screens.analytics.AnalyticsScreen
import com.darleyleal.exitto.presentation.screens.habits.HabitsScreen
import com.darleyleal.exitto.presentation.screens.home.HomeScreen
import com.darleyleal.exitto.presentation.screens.profile.ProfileScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedItemIndex = {
                    selectedItemIndex = it
                }
            )
        },
        content = { paddingValues ->

            when (selectedItemIndex) {
                0 -> {
                    HomeScreen(paddingValues = paddingValues)
                }

                1 -> {
                    HabitsScreen(paddingValues = paddingValues)
                }

                2 -> {
                    AnalyticsScreen(paddingValues = paddingValues)
                }

                3 -> {
                    ProfileScreen(paddingValues = paddingValues)
                }
            }
        }
    )
}