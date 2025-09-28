package com.darleyleal.exitto.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String,
    val name: String,
    val dateOfBirthday: LocalDate,
    val sex: String,
    val heightCm: Double,
    val weightKg: Double,
)