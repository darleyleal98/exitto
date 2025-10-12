package com.darleyleal.exitto.presentation.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darleyleal.exitto.domain.entity.RegisterForm
import com.darleyleal.exitto.domain.entity.RegisterResult
import com.darleyleal.exitto.domain.entity.RegisterUiState
import com.darleyleal.exitto.domain.usecase.RegisterUserUseCase
import com.darleyleal.exitto.domain.usecase.ValidateRegisterFormUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for user registration screen following Clean Architecture principles.
 * 
 * Key improvements:
 * - Single immutable UiState instead of multiple StateFlows
 * - Separation of concerns: ViewModel handles UI state, UseCase handles business logic
 * - Dependency injection of UseCases instead of direct Firebase access
 * - Proper error handling with specific error messages
 * - Testable architecture with clear responsibilities
 */
@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val validateRegisterFormUseCase: ValidateRegisterFormUseCase
) : ViewModel() {

    // Single source of truth for UI state
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    /**
     * Updates the email field in the form and validates it.
     */
    fun updateEmail(email: String) {
        updateForm { form ->
            form.copy(email = email)
        }
        validateForm()
    }

    /**
     * Updates the password field in the form and validates it.
     */
    fun updatePassword(password: String) {
        updateForm { form ->
            form.copy(password = password)
        }
        validateForm()
    }

    /**
     * Updates the confirm password field in the form and validates it.
     */
    fun updateConfirmPassword(confirmPassword: String) {
        updateForm { form ->
            form.copy(confirmPassword = confirmPassword)
        }
        validateForm()
    }

    /**
     * Attempts to register the user with the current form data.
     * Uses the RegisterUserUseCase to handle the business logic.
     */
    fun registerUser() {
        val currentForm = _uiState.value.form
        viewModelScope.launch {
            registerUserUseCase(currentForm).collect { result ->
                updateUiState { currentState ->
                    currentState.copy(result = result)
                }
            }
        }
    }

    /**
     * Validates the current form data and updates validation errors.
     * Uses the ValidateRegisterFormUseCase for consistent validation logic.
     */
    private fun validateForm() {
        val currentForm = _uiState.value.form
        val validationErrors = validateRegisterFormUseCase(currentForm)
        
        updateUiState { currentState ->
            currentState.copy(validationErrors = validationErrors)
        }
    }

    /**
     * Updates the form data using the provided transformation function.
     * Ensures immutability by creating a new form instance.
     */
    private fun updateForm(transform: (RegisterForm) -> RegisterForm) {
        updateUiState { currentState ->
            currentState.copy(form = transform(currentState.form))
        }
    }

    /**
     * Updates the UI state using the provided transformation function.
     * Ensures immutability by creating a new state instance.
     */
    private fun updateUiState(transform: (RegisterUiState) -> RegisterUiState) {
        _uiState.value = transform(_uiState.value)
    }

    /**
     * Checks if the current form is valid for submission.
     * This is a convenience method for the UI layer.
     */
    fun isFormValid(): Boolean = _uiState.value.isFormValid

    /**
     * Checks if the registration can be submitted.
     * Prevents multiple submissions while registration is in progress.
     */
    fun canSubmit(): Boolean = _uiState.value.canSubmit

    /**
     * Gets the current error message if registration failed.
     * Returns null if there's no error or if registration is successful.
     */
    fun getErrorMessage(): String? {
        val result = _uiState.value.result
        return if (result is RegisterResult.Error) result.message else null
    }

    /**
     * Checks if registration was successful.
     * Convenience method for the UI layer.
     */
    fun isRegistrationSuccessful(): Boolean {
        return _uiState.value.result is RegisterResult.Success
    }

    /**
     * Checks if registration is currently in progress.
     * Convenience method for the UI layer.
     */
    fun isLoading(): Boolean {
        return _uiState.value.result is RegisterResult.Loading
    }
}