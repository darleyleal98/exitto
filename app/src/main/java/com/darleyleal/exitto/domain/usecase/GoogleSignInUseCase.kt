package com.darleyleal.exitto.domain.usecase

import com.darleyleal.exitto.domain.entity.RegisterResult
import com.darleyleal.exitto.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for Google Sign-In authentication.
 * Handles the complete Google Sign-In flow including Firebase integration.
 * Following Clean Architecture principles with single responsibility.
 */
class GoogleSignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    
    /**
     * Executes Google Sign-In authentication.
     * 
     * @param idToken Google ID token from the sign-in result
     * @return Flow of RegisterResult indicating success, error, or loading state
     */
    suspend operator fun invoke(idToken: String): Flow<RegisterResult> {
        return authRepository.signInWithGoogle(idToken)
    }
}

