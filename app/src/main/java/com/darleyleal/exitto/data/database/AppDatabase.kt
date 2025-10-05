package com.darleyleal.exitto.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.darleyleal.exitto.data.dao.UserDao
import com.darleyleal.exitto.data.entity.UserEntity
import com.darleyleal.exitto.domain.entity.User

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}