package com.darleyleal.exitto.domain.usecase

import com.darleyleal.exitto.domain.entity.RegisterForm
import com.darleyleal.exitto.domain.entity.RegisterResult
import com.darleyleal.exitto.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Use case for user registration.
 * Follows Clean Architecture principle of single responsibility.
 * Encapsulates the business logic for user registration.
 */
class RegisterUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    
    /**
     * Executes user registration with validation.
     * 
     * @param form The registration form data
     * @return Flow of RegisterResult
     */
    suspend operator fun invoke(form: RegisterForm): Flow<RegisterResult> = flow {
        // Validate form before proceeding
        val validationResult = validateForm(form)
        if (validationResult !is RegisterResult.Success) {
            emit(validationResult)
            return@flow
        }
        
        // Emit loading state
        emit(RegisterResult.Loading)
        
        // Perform registration
        authRepository.registerUser(form.email, form.password).collect { result ->
            when (result) {
                is RegisterResult.Success -> {
                    // Save login status on successful registration
                    authRepository.saveLoginStatus(true)
                    emit(result)
                }
                is RegisterResult.Error -> emit(result)
                is RegisterResult.Loading -> emit(result)
            }
        }
    }
    
    /**
     * Validates the registration form.
     * Separated validation logic for reusability and testability.
     */
    private fun validateForm(form: RegisterForm): RegisterResult {
        return when {
            form.email.isBlank() -> RegisterResult.Error("Email is required")
            !isValidEmail(form.email) -> RegisterResult.Error("Please enter a valid email address")
            form.password.isBlank() -> RegisterResult.Error("Password is required")
            form.password.length < 6 -> RegisterResult.Error("Password must be at least 6 characters")
            form.confirmPassword.isBlank() -> RegisterResult.Error("Please confirm your password")
            form.password != form.confirmPassword -> RegisterResult.Error("Passwords do not match")
            else -> RegisterResult.Success
        }
    }
    
    /**
     * Validates email format using Android's built-in pattern.
     */
    private fun isValidEmail(email: String): Boolean {
        val pattern = android.util.Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}

