package com.darleyleal.exitto.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darleyleal.exitto.domain.entity.RegisterResult
import com.darleyleal.exitto.domain.repository.AuthRepository
import com.darleyleal.exitto.domain.usecase.GoogleSignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val googleSignInUseCase: GoogleSignInUseCase
) : ViewModel() {

    private val _isSuccessful = MutableStateFlow<Boolean>(false)
    val isSuccessful: StateFlow<Boolean> = _isSuccessful

    private val _isFailure = MutableStateFlow<Boolean>(false)
    val isFailure: StateFlow<Boolean> = _isFailure

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _googleSignInResult = MutableStateFlow<RegisterResult?>(null)
    val googleSignInResult: StateFlow<RegisterResult?> = _googleSignInResult

    fun signInWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            try {
                _isSuccessful.value = false
                _isFailure.value = false
                _errorMessage.value = null

                // For now, we'll use Firebase Auth directly since we don't have a login use case yet
                // In a full implementation, you'd create a SignInUseCase similar to RegisterUserUseCase
                val auth = com.google.firebase.auth.FirebaseAuth.getInstance()
                val result = auth.signInWithEmailAndPassword(email, password).await()
                
                if (result.user != null) {
                    // Save login status to DataStore
                    authRepository.saveLoginStatus(true)
                    _isSuccessful.value = true
                    _isFailure.value = false
                }
            } catch (e: Exception) {
                _isFailure.value = true
                _isSuccessful.value = false
                _errorMessage.value = "Login failed: ${e.message}"
            }
        }
    }

    /**
     * Signs in user with Google ID token.
     * 
     * @param idToken Google ID token from Google Sign-In result
     */
    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            try {
                _googleSignInResult.value = null
                _isSuccessful.value = false
                _isFailure.value = false
                _errorMessage.value = null

                googleSignInUseCase(idToken).collect { result ->
                    _googleSignInResult.value = result
                    
                    when (result) {
                        is RegisterResult.Success -> {
                            _isSuccessful.value = true
                            _isFailure.value = false
                        }
                        is RegisterResult.Error -> {
                            _isFailure.value = true
                            _isSuccessful.value = false
                            _errorMessage.value = result.message
                        }
                        is RegisterResult.Loading -> {
                            // Handle loading state if needed
                        }
                    }
                }
            } catch (e: Exception) {
                _isFailure.value = true
                _isSuccessful.value = false
                _errorMessage.value = "Google Sign-In failed: ${e.message}"
                _googleSignInResult.value = RegisterResult.Error("Google Sign-In failed: ${e.message}")
            }
        }
    }
}