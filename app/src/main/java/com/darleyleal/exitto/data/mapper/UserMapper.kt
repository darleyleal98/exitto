package com.darleyleal.exitto.data.mapper

import com.darleyleal.exitto.data.entity.UserEntity
import com.darleyleal.exitto.domain.entity.Sex
import com.darleyleal.exitto.domain.entity.User

fun UserEntity.toDomain() = User(
    id = id,
    name = name,
    dateOfBirthday = dateOfBirthday,
    sex = Sex.valueOf(sex),
    heightCm = heightCm,
    weightKg = weightKg
)

fun User.toEntity() = UserEntity(
    id = id,
    name = name,
    dateOfBirthday = dateOfBirthday,
    sex = sex.name,
    heightCm  = heightCm,
    weightKg = weightKg
)