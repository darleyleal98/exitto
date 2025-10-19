package com.darleyleal.exitto.presentation.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darleyleal.exitto.domain.entity.RegisterResult
import com.darleyleal.exitto.domain.entity.User
import com.darleyleal.exitto.domain.repository.AuthRepository
import com.darleyleal.exitto.domain.usecase.CreateUserProfileUseCase
import com.darleyleal.exitto.domain.usecase.GoogleSignInUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val googleSignInUseCase: GoogleSignInUseCase,
    private val createUserUseCase: CreateUserProfileUseCase
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

                val auth = com.google.firebase.auth.FirebaseAuth.getInstance()
                val result = auth.signInWithEmailAndPassword(email, password).await()

                if (result.user != null) {
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
                            val firebaseUser = FirebaseAuth.getInstance().currentUser
                            firebaseUser?.let { user ->
                                try {
                                    createUserUseCase(
                                        name = user.displayName,
                                        dateOfBirthday = null,
                                        sex = null,
                                        imagePath = user.photoUrl?.toString(),
                                        heightCm = null,
                                        weightKg = null
                                    )
                                } catch (e: Exception) {
                                    Log.e("LoginViewModel", "Erro ao criar perfil do usuário", e)
                                }
                            } ?: run {
                                Log.w("LoginViewModel", "FirebaseUser é null após login!")
                            }

                            _isSuccessful.value = true
                            _isFailure.value = false
                        }

                        is RegisterResult.Error -> {
                            Log.e("LoginViewModel", "Google login ERROR: ${result.message}")
                            _isFailure.value = true
                            _isSuccessful.value = false
                            _errorMessage.value = result.message
                        }

                        is RegisterResult.Loading -> {
                            Log.d("LoginViewModel", "Google login LOADING...")
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Exceção no signInWithGoogle", e)
                _isFailure.value = true
                _isSuccessful.value = false
                _errorMessage.value = "Google Sign-In failed: ${e.message}"
                _googleSignInResult.value = RegisterResult.Error("Google Sign-In failed: ${e.message}")
            }
        }
    }
}