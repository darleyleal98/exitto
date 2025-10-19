package com.darleyleal.exitto.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserProfileEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "date_of_birthday")
    val dateOfBirthday: String? = null,

    @ColumnInfo(name = "image_path")
    val imagePath: String? = null,

    @ColumnInfo(name = "sex")
    val sex: String? = null,

    @ColumnInfo(name = "height_cm")
    val heightCm: Double? = null,

    @ColumnInfo(name = "weight_kg")
    val weightKg: Double? = null
)