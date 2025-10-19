package com.darleyleal.exitto.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.darleyleal.exitto.data.dao.UserDao
import com.darleyleal.exitto.data.entity.UserProfileEntity

@Database(
    entities = [UserProfileEntity::class],
    version = 8, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}