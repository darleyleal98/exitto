package com.darleyleal.exitto.domain.entity

import java.time.LocalDate

enum class Sex { MALE, FEMALE, OTHER }

data class User(
    val id: Int,
    val name: String,
    val dateOfBirthday: LocalDate,
    val sex: Sex,
    val heightCm: Double,
    val weightKg: Double
) {
    val age: Int get() = LocalDate.now().year - dateOfBirthday.year
    val bmi: Double get() = weightKg / ((heightCm / 100) * (heightCm / 100))
}
