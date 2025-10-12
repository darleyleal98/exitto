package com.darleyleal.exitto.presentation.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darleyleal.exitto.domain.usecase.CheckAuthStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for managing authentication state across the app.
 * Handles checking if user is logged in and provides authentication status.
 * Following Clean Architecture principles with single responsibility.
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val checkAuthStatusUseCase: CheckAuthStatusUseCase
) : ViewModel() {

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        checkAuthStatus()
    }

    /**
     * Checks the current authentication status.
     * This is called when the ViewModel is created to determine initial navigation.
     */
    private fun checkAuthStatus() {
        viewModelScope.launch {
            checkAuthStatusUseCase().collect { isAuthenticated ->
                _isAuthenticated.value = isAuthenticated
                _isLoading.value = false
            }
        }
    }

    /**
     * Refreshes the authentication status.
     * Can be called when needed to re-check authentication state.
     */
    fun refreshAuthStatus() {
        _isLoading.value = true
        checkAuthStatus()
    }
}

