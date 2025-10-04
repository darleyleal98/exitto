package com.darleyleal.exitto.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.darleyleal.exitto.data.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserDao {
    
    @Insert
    suspend fun insert(user: UserEntity)

    @Update
    suspend fun update(user: UserEntity)

    suspend fun getUserById(id: Int): Flow<UserEntity?>
}