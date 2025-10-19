package com.darleyleal.exitto.data.repository

import android.util.Log
import com.darleyleal.exitto.data.datastore.LoginPreferences
import com.darleyleal.exitto.domain.entity.RegisterResult
import com.darleyleal.exitto.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Firebase implementation of AuthRepository.
 */

const val TAG = "AUTH_REPOSITORY"

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val loginPreferences: LoginPreferences
) : AuthRepository {

    /**
     * Registers a new user with email and password.
     * Returns a Flow to handle the registration result reactively.
     * @param email User's email address
     * @param password User's password
     **/
    override suspend fun registerUser(email: String, password: String): Flow<RegisterResult> = flow {
        emit(RegisterResult.Loading)
        val result = try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            if (authResult.user != null) RegisterResult.Success
            else RegisterResult.Error("Failed to create user account.")
        } catch (e: Exception) {
            RegisterResult.Error("Registration failed: ${e.message ?: "Unknown error."}")
        }
        emit(result)
    }

    /**
     * Saves user login status to local storage.
     * @param isLoggedIn Whether the user is logged in
     * */
    override suspend fun saveLoginStatus(isLoggedIn: Boolean) {
        loginPreferences.setLoggedIn(isLoggedIn)
    }

    /**
     * Gets the current authenticated user from Firebase.
     * @return Flow of FirebaseUser or null if not authenticated
     *
     * */
    override fun getCurrentUser(): Flow<FirebaseUser?> = flow {
        emit(firebaseAuth.currentUser)
    }

    override suspend fun signInWithGoogle(idToken: String): Flow<RegisterResult> = flow {
        emit(RegisterResult.Loading)
        val result = try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val authResult = firebaseAuth.signInWithCredential(credential).await()
            val user = authResult.user

            if (user != null) {
                saveLoginStatus(true)
                Log.i(TAG, "User data: ${user.email}\n${user.displayName}\n${user.photoUrl}")
                RegisterResult.Success
            } else {
                RegisterResult.Error("Google Sign-In failed: no user returned.")
            }
        } catch (e: Exception) {
            RegisterResult.Error("Google Sign-In failed: ${e.message ?: "Unknown error."}")
        }
        emit(result)
    }
}
