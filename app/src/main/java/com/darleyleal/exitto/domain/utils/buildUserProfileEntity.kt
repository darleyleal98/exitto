package com.darleyleal.exitto.domain.utils

import com.darleyleal.exitto.data.entity.UserProfileEntity

fun buildUserProfileEntity(
    name: String?,
    dateOfBirthday: String?,
    sex: String?,
    heightCm: Double?,
    weightKg: Double?,
    imagePath: String?,
): UserProfileEntity = UserProfileEntity(
    name = name, imagePath = imagePath,
    dateOfBirthday = dateOfBirthday, sex = sex,
    heightCm = heightCm, weightKg = weightKg
)