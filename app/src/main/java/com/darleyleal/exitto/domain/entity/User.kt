package com.darleyleal.exitto.domain.entity

import java.time.LocalDate

enum class Sex { MALE, FEMALE, OTHER }

data class User(
    val id: Int,
    val name: String,
    val dateOfBirthday: String,
    val sex: Sex,
    val heightCm: Double,
    val weightKg: Double
)