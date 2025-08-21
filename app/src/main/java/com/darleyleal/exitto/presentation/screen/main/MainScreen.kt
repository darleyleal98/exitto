package com.darleyleal.exitto.presentation.screen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.darleyleal.exitto.presentation.component.BottomNavigationBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(modifier: Modifier = Modifier, innerPadding: PaddingValues, navController: NavHostController) {
    Scaffold(
        modifier = modifier.padding(innerPadding),
        content = {
            Text(text = "Main Screen teste")
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    )
}