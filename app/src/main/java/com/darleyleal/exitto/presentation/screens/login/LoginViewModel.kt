package com.darleyleal.exitto.presentation.screens.login

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _isSuccesfull = MutableStateFlow<Boolean>(false)
    val isSuccesfull: StateFlow<Boolean> = _isSuccesfull

    private val _isFailure = MutableStateFlow<Boolean>(false)
    val isFailure: StateFlow<Boolean> = _isFailure

    private val auth = FirebaseAuth.getInstance()

    fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            when {
                task.isSuccessful -> _isSuccesfull.value = true
                else -> _isFailure.value = true
            }
        }
    }
}