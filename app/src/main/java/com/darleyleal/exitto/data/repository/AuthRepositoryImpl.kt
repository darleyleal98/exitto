package com.darleyleal.exitto.data.repository

import com.darleyleal.exitto.data.datastore.LoginPreferences
import com.darleyleal.exitto.domain.entity.RegisterResult
import com.darleyleal.exitto.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of AuthRepository using Firebase Authentication.
 * Follows Clean Architecture principle of dependency inversion.
 * The data layer implements the domain repository interface.
 */
@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val loginPreferences: LoginPreferences
) : AuthRepository {
    
    override suspend fun registerUser(email: String, password: String): Flow<RegisterResult> = flow {
        try {
            // Create user with Firebase Authentication
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val user = authResult.user
            
            if (user != null) {
                emit(RegisterResult.Success)
            } else {
                emit(RegisterResult.Error("Failed to create user account"))
            }

        } catch (e: FirebaseAuthUserCollisionException) {
            emit(RegisterResult.Error("This email is already registered. Please use a different email."))
        } catch (e: FirebaseAuthWeakPasswordException) {
            emit(RegisterResult.Error("Password is too weak. Please choose a stronger password."))
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            emit(RegisterResult.Error("Invalid email format. Please check your email address."))
        } catch (e: Exception) {
            emit(RegisterResult.Error("Registration failed: ${e.message ?: "Unknown error occurred"}"))
        }
    }
    
    override suspend fun saveLoginStatus(isLoggedIn: Boolean) {
        loginPreferences.writeToDataStore(isLoggedIn.toString())
    }
    
    override fun getCurrentUser(): Flow<FirebaseUser?> {
        return flow {
            emit(firebaseAuth.currentUser)
        }
    }
    
    override suspend fun signInWithGoogle(idToken: String): Flow<RegisterResult> = flow {
        try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val authResult = firebaseAuth.signInWithCredential(credential).await()
            val user = authResult.user
            
            if (user != null) {
                // Save login status to DataStore
                saveLoginStatus(true)
                emit(RegisterResult.Success)
            } else {
                emit(RegisterResult.Error("Google Sign-In failed: No user returned"))
            }
        } catch (e: Exception) {
            emit(RegisterResult.Error("Google Sign-In failed: ${e.message ?: "Unknown error occurred"}"))
        }
    }
}
