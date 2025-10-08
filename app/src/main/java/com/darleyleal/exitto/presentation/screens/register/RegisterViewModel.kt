package com.darleyleal.exitto.presentation.screens.register

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
) : ViewModel() {

    private var _isSuccess = MutableStateFlow<Boolean>(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess

    private var _isFailure = MutableStateFlow<Boolean>(false)
    val isFailure: StateFlow<Boolean> = _isFailure

    fun saveUser(email: String, password: String) {
        val user = hashMapOf(
            "email" to email,
            "password" to password
        )

        Log.i("Register", "saveUser: $user")

        firestore.collection("users")
            .add(user)
            .addOnSuccessListener {
                _isSuccess.value = true
                _isFailure.value = false
            }
            .addOnFailureListener {
                _isFailure.value = true
                _isSuccess.value = false
            }
    }
}