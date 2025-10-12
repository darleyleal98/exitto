package com.darleyleal.exitto.domain.entity

/**
 * Domain entity representing the registration form data.
 * Immutable data class following Clean Architecture principles.
 */
data class RegisterForm(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = ""
)

