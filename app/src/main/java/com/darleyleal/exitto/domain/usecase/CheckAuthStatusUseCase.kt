package com.darleyleal.exitto.domain.usecase

import com.darleyleal.exitto.data.datastore.LoginPreferences
import com.darleyleal.exitto.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

/**
 * Use case to check if user is authenticated.
 * Combines Firebase Auth state with local DataStore to determine authentication status.
 * This ensures consistency between Firebase and local storage.
 */
class CheckAuthStatusUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val loginPreferences: LoginPreferences
) {
    /**
     * Checks if user is currently authenticated.
     * Returns a Flow that emits true if user is authenticated, false otherwise.
     */
    operator fun invoke(): Flow<Boolean> {
        return combine(
            authRepository.getCurrentUser(),
            loginPreferences.readFromDataStore()
        ) { firebaseUser, loginStatus ->
            // User is authenticated if Firebase has a user AND local storage confirms login
            firebaseUser != null && loginStatus == "true"
        }
    }
}