package com.darleyleal.exitto.domain.usecase

import com.darleyleal.exitto.domain.entity.RegisterForm
import com.darleyleal.exitto.domain.entity.ValidationErrors
import javax.inject.Inject

/**
 * Use case for validating registration form data.
 * Follows Single Responsibility Principle - only handles validation logic.
 * This makes validation reusable and easily testable.
 */
class ValidateRegisterFormUseCase @Inject constructor() {
    
    /**
     * Validates the registration form and returns validation errors.
     * 
     * @param form The form data to validate
     * @return ValidationErrors object containing any validation issues
     */
    operator fun invoke(form: RegisterForm): ValidationErrors {
        return ValidationErrors(
            emailError = validateEmail(form.email),
            passwordError = validatePassword(form.password),
            confirmPasswordError = validateConfirmPassword(form.password, form.confirmPassword)
        )
    }
    
    /**
     * Validates email field.
     */
    private fun validateEmail(email: String): String? {
        return when {
            email.isBlank() -> "Email is required"
            !isValidEmail(email) -> "Please enter a valid email address"
            else -> null
        }
    }
    
    /**
     * Validates password field.
     */
    private fun validatePassword(password: String): String? {
        return when {
            password.isBlank() -> "Password is required"
            password.length < 6 -> "Password must be at least 6 characters"
            else -> null
        }
    }
    
    /**
     * Validates confirm password field.
     */
    private fun validateConfirmPassword(password: String, confirmPassword: String): String? {
        return when {
            confirmPassword.isBlank() -> "Please confirm your password"
            password != confirmPassword -> "Passwords do not match"
            else -> null
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

