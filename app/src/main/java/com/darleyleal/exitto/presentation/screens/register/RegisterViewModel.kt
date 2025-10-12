package com.darleyleal.exitto.presentation.screens.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val auth: FirebaseAuth,
) : ViewModel() {
    private var _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private var _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private var _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword

    private var _isSuccess = MutableStateFlow<Boolean>(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess

    private var _isFailure = MutableStateFlow<Boolean>(false)
    val isFailure: StateFlow<Boolean> = _isFailure

    private var _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun createUserWithEmailAndPasswordInFirebase(email: String, password: String) {
        viewModelScope.launch {
            try {
                val authResult = auth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        _isSuccess.value = true
                    }.addOnFailureListener {
                        _isFailure.value = true
                    }

                val user = authResult.await().user

            } catch (e: Exception) {
                _isFailure.value = true
                _isSuccess.value = false
                _errorMessage.value = "Registration failed: ${e.message}"
            }
        }
    }

    fun saveUserToLocal() {
        viewModelScope.launch {

        }
    }

    fun updateEmail(email: String) {
        _email.value = email
    }

    fun updatePassword(password: String) {
        _password.value = password
    }

    fun updateConfirmPassword(confirmPassword: String) {
        _confirmPassword.value = confirmPassword
    }

    fun validateIfEmailIsInvalid(text: String): Boolean = !Patterns.EMAIL_ADDRESS.matcher(text).matches()

    fun validatePassword(): Boolean = password.value.length >= 6 && password.value == confirmPassword.value

    fun validateEmail(): Boolean = email.value.isNotBlank() && !validateIfEmailIsInvalid(email.value)

    fun validateAllFields(): Boolean = validateEmail() && validatePassword() && confirmPassword.value.isNotBlank()
}