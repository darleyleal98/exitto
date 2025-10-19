package com.darleyleal.exitto.domain.entity

enum class Sex { MALE, FEMALE, OTHER }

data class User(
    val id: Int,
    val name: String,
    val dateOfBirthday: String,
    val imagePath: String? = null,
    val sex: Sex,
    val heightCm: Double,
    val weightKg: Double
)