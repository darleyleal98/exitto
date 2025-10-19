package com.darleyleal.exitto.domain.utils

fun validateUserProfile(
    name: String?,
    dateOfBirthday: String?,
    sex: String?,
    heightCm: Double?,
    weightKg: Double?,
    imagePath: String?,
    strict: Boolean = true
) {
    require(!name.isNullOrBlank()) { "Name can't be empty" }

    if (strict) {
        require(!dateOfBirthday.isNullOrBlank()) { "Date of birthday is required" }
        require(!sex.isNullOrBlank()) { "Sex must be provided" }
        require(heightCm != null && heightCm > 0) { "Height must be a positive number" }
        require(weightKg != null && weightKg > 0) { "Weight must be a positive number" }
    }
}