package com.darleyleal.exitto.presentation.provider

import androidx.lifecycle.ViewModel
import com.darleyleal.exitto.presentation.screens.analytics.AnalyticsViewModel
import com.darleyleal.exitto.presentation.screens.register.AuthViewModel
import com.darleyleal.exitto.presentation.screens.gym.GymViewModel
import com.darleyleal.exitto.presentation.screens.health.HealthViewModel
import com.darleyleal.exitto.presentation.screens.home.HomeViewModel
import com.darleyleal.exitto.presentation.screens.login.LoginViewModel
import com.darleyleal.exitto.presentation.screens.main.MainViewModel
import com.darleyleal.exitto.presentation.screens.profile.ProfileViewModel
import com.darleyleal.exitto.presentation.screens.register.RegisterViewModel
import javax.inject.Inject

class ViewModelProvider @Inject constructor(
    authViewModel: AuthViewModel,
    analyticsViewModel: AnalyticsViewModel,
    healthViewModel: HealthViewModel,
    registerViewModel: RegisterViewModel,
    loginViewModel: LoginViewModel,
    homeViewModel: HomeViewModel,
    profileViewModel: ProfileViewModel,
    gymViewModel: GymViewModel,
    mainViewModel: MainViewModel
) : ViewModel() {

    private val viewModels: Map<ViewModelKey, ViewModel> = mapOf(
        ViewModelKey.AUTH to authViewModel,
        ViewModelKey.ANALYTICS to analyticsViewModel,
        ViewModelKey.HEALTH to healthViewModel,
        ViewModelKey.REGISTER to registerViewModel,
        ViewModelKey.LOGIN to loginViewModel,
        ViewModelKey.HOME to homeViewModel,
        ViewModelKey.PROFILE to profileViewModel,
        ViewModelKey.GYM to gymViewModel,
        ViewModelKey.MAIN to mainViewModel
    )

    fun getViewModel(key: ViewModelKey): ViewModel {
        return viewModels[key] ?: throw IllegalArgumentException("Unknown ViewModel key: $key")
    }
}