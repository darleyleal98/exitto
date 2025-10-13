package com.darleyleal.exitto.presentation.navigation

import android.window.SplashScreen
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.darleyleal.exitto.presentation.provider.ViewModelKey
import com.darleyleal.exitto.presentation.provider.ViewModelProvider
import com.darleyleal.exitto.presentation.screens.analytics.AnalyticsScreen
import com.darleyleal.exitto.presentation.screens.register.AuthViewModel
import com.darleyleal.exitto.presentation.screens.gym.GymScreen
import com.darleyleal.exitto.presentation.screens.health.HealthScreen
import com.darleyleal.exitto.presentation.screens.home.HomeScreen
import com.darleyleal.exitto.presentation.screens.login.LoginScreen
import com.darleyleal.exitto.presentation.screens.main.MainScreen
import com.darleyleal.exitto.presentation.screens.profile.ProfileScreen
import com.darleyleal.exitto.presentation.screens.register.RegisterScreen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    auth: FirebaseAuth,
    viewModelProvider: ViewModelProvider
) {
    val navController = rememberNavController()
    val authViewModel = viewModelProvider.getViewModel(ViewModelKey.AUTH) as AuthViewModel

    val isAuthenticated by authViewModel.isAuthenticated.collectAsState()
    val isLoading by authViewModel.isLoading.collectAsState()

    when {
        isLoading -> SplashScreen()

        else -> {
            NavHost(startDestination = if (isAuthenticated) Routes.Main.name else Routes.Login.name, navController = navController) {
                composable(route = Routes.Login.name) {
                    LoginScreen(
                        viewModelProvider = viewModelProvider,
                        onNavigateToRegisterScreen = {
                            navController.navigate(Routes.Register.name)
                        },
                        onNavigateToMainScreen = {
                            navController.navigate(Routes.Main.name) {
                                popUpTo(Routes.Login.name) { inclusive = true }
                            }
                        }
                    )
                }
                composable(
                    route = Routes.Register.name,
                    enterTransition = {
                        slideIntoContainer(
                            animationSpec = tween(durationMillis = 400, easing = EaseIn),
                            towards = AnimatedContentTransitionScope.SlideDirection.Start
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            animationSpec = tween(400, easing = LinearEasing),
                            towards = AnimatedContentTransitionScope.SlideDirection.End
                        )
                    }
                ) {
                    RegisterScreen(
                        modifier,
                        viewModelProvider = viewModelProvider,
                        onPopBackStack = {
                            navController.popBackStack()
                        },
                        onNavigateToMainScreen = {
                            navController.navigate(Routes.Main.name)
                        }
                    )
                }
                composable(route = Routes.Main.name) {
                    MainScreen(
                        modifier, navController, viewModelProvider = viewModelProvider,
                    )
                }
                composable(route = Routes.Home.name) {
                    HomeScreen(
                        modifier, viewModelProvider = viewModelProvider,
                    )
                }
                composable(route = Routes.Health.name) {
                    HealthScreen(
                        modifier, viewModelProvider = viewModelProvider,
                    )
                }
                composable(route = Routes.Analytics.name) {
                    AnalyticsScreen(
                        modifier, viewModelProvider = viewModelProvider,
                    )
                }
                composable(route = Routes.Gym.name) {
                    GymScreen(
                        modifier, viewModelProvider = viewModelProvider,
                    )
                }
                composable(route = Routes.Profile.name) {
                    ProfileScreen(
                        modifier, viewModelProvider = viewModelProvider,
                    )
                }
            }
        }
    }
}

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Loading...",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}