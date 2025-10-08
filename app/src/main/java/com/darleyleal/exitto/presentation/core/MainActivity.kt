package com.darleyleal.exitto.presentation.core

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.darleyleal.exitto.presentation.core.theme.ExittoTheme
import com.darleyleal.exitto.presentation.navigation.AppNavigation
import com.darleyleal.exitto.presentation.navigation.Routes
import com.darleyleal.exitto.presentation.provider.ViewModelProvider
import com.darleyleal.exitto.presentation.screens.analytics.AnalyticsViewModel
import com.darleyleal.exitto.presentation.screens.gym.GymViewModel
import com.darleyleal.exitto.presentation.screens.health.HealthViewModel
import com.darleyleal.exitto.presentation.screens.home.HomeViewModel
import com.darleyleal.exitto.presentation.screens.login.LoginViewModel
import com.darleyleal.exitto.presentation.screens.main.MainViewModel
import com.darleyleal.exitto.presentation.screens.profile.ProfileViewModel
import com.darleyleal.exitto.presentation.screens.register.RegisterViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        auth = Firebase.auth

        val analyticsViewModel: AnalyticsViewModel by viewModels()
        val healthViewModel: HealthViewModel by viewModels()
        val loginViewModel: LoginViewModel by viewModels()
        val homeViewModel: HomeViewModel by viewModels()
        val profileViewModel: ProfileViewModel by viewModels()
        val gymViewModel: GymViewModel by viewModels()
        val mainViewModel: MainViewModel by viewModels()
        val registerViewModel: RegisterViewModel by viewModels()

        val viewModelProvider = ViewModelProvider(
            analyticsViewModel = analyticsViewModel,
            healthViewModel = healthViewModel,
            loginViewModel = loginViewModel,
            homeViewModel = homeViewModel,
            profileViewModel = profileViewModel,
            gymViewModel = gymViewModel,
            mainViewModel = mainViewModel,
            registerViewModel = registerViewModel
        )

        setContent {
            ExittoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    LaunchedEffect(Unit) {
                        if (auth.currentUser != null) {
                            navController.navigate(Routes.Login.name) {
                                popUpTo(Routes.Login.name) {
                                    inclusive = true
                                }
                            }
                        }
                    }

                    AppNavigation(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        auth = auth,
                        viewModelProvider = viewModelProvider
                    )
                }
            }
        }
    }
}