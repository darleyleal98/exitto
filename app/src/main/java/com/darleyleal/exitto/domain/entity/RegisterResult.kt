package com.darleyleal.exitto.domain.entity

/**
 * Represents the result of a user registration operation.
 * Following Clean Architecture principles by keeping domain entities pure.
 */
sealed class RegisterResult {
    object Success : RegisterResult()
    data class Error(val message: String) : RegisterResult()
    object Loading : RegisterResult()
}

