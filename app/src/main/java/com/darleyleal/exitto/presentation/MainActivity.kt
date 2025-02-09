package com.darleyleal.exitto.presentation

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.darleyleal.exitto.presentation.navigation.AppNavigation
import com.darleyleal.exitto.presentation.theme.ExittoTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            requestedOrientation = SCREEN_ORIENTATION_PORTRAIT

            ExittoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(
                        context = this,
                        navController = navController,
                        paddingValues = innerPadding
                    )
                }
            }
        }
    }
}