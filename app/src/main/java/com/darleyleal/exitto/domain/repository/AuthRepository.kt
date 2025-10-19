package com.darleyleal.exitto.domain.repository

import com.darleyleal.exitto.domain.entity.RegisterResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for authentication operations.
 */
interface AuthRepository {
    
    /**
     * Registers a new user with email and password.
     * Returns a Flow to handle the registration result reactively.
     * 
     * @param email User's email address
     * @param password User's password
     * @return Flow of RegisterResult indicating success, error, or loading state
     */
    suspend fun registerUser(email: String, password: String): Flow<RegisterResult>
    
    /**
     * Saves user login status to local storage.
     * 
     * @param isLoggedIn Whether the user is logged in
     */
    suspend fun saveLoginStatus(isLoggedIn: Boolean)
    
    /**
     * Gets the current authenticated user from Firebase.
     * 
     * @return Flow of FirebaseUser or null if not authenticated
     */
    fun getCurrentUser(): Flow<FirebaseUser?>
    
    /**
     * Signs in user with Google ID token.
     * 
     * @param idToken Google ID token from Google Sign-In
     * @return Flow of RegisterResult indicating success, error, or loading state
     */
    suspend fun signInWithGoogle(idToken: String): Flow<RegisterResult>
}
