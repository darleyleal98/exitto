package com.darleyleal.exitto.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.darleyleal.exitto.presentation.screens.analytics.AnalyticsScreen
import com.darleyleal.exitto.presentation.screens.gym.GymScreen
import com.darleyleal.exitto.presentation.screens.health.HealthScreen
import com.darleyleal.exitto.presentation.screens.home.HomeScreen
import com.darleyleal.exitto.presentation.screens.login.LoginScreen
import com.darleyleal.exitto.presentation.screens.main.MainScreen
import com.darleyleal.exitto.presentation.screens.profile.ProfileScreen
import com.darleyleal.exitto.presentation.screens.register.RegisterScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(startDestination = Routes.Login.name, navController = navController) {
        composable(route = Routes.Login.name) {
            LoginScreen(modifier, onNavigateToRegisterScreen = {
                navController.navigate(Routes.Register.name)
            })
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
            RegisterScreen(modifier, onPopBackStack = {
                navController.popBackStack()
            })
        }
        composable(route = Routes.Main.name) {
            MainScreen(modifier, navController)
        }
        composable(route = Routes.Home.name) {
            HomeScreen(modifier)
        }
        composable(route = Routes.Health.name) {
            HealthScreen(modifier)
        }
        composable(route = Routes.Analytics.name) {
            AnalyticsScreen(modifier)
        }
        composable(route = Routes.Gym.name) {
            GymScreen(modifier)
        }
        composable(route = Routes.Profile.name) {
            ProfileScreen(modifier)
        }
    }
}