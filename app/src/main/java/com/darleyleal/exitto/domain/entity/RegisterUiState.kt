package com.darleyleal.exitto.domain.entity

/**
 * Immutable UI state for the registration screen.
 * Follows the principle of having a single source of truth for UI state.
 * This replaces multiple StateFlows with a single, cohesive state object.
 */
data class RegisterUiState(
    val form: RegisterForm = RegisterForm(),
    val result: RegisterResult? = null,
    val validationErrors: ValidationErrors = ValidationErrors()
) {
    val isFormValid: Boolean
        get() = validationErrors.isEmpty && form.email.isNotBlank() &&
                form.password.isNotBlank() && form.confirmPassword.isNotBlank()
    
    val canSubmit: Boolean
        get() = isFormValid && result !is RegisterResult.Loading
}

/**
 * Validation errors for the registration form.
 * Immutable data class containing all validation error messages.
 */
data class ValidationErrors(
    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null
) {
    val isEmpty: Boolean
        get() = emailError == null && passwordError == null && confirmPasswordError == null
}

