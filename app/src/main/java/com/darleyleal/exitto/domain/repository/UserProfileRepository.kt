package com.darleyleal.exitto.domain.repository

import com.darleyleal.exitto.data.entity.UserProfileEntity
import com.darleyleal.exitto.domain.entity.User
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for user profile data.
 * */
interface UserProfileRepository {

    /**
     * Creates a new user profile in the database.
     * @param user The user profile to be created.
     * */
    suspend fun createUserProfile(user: UserProfileEntity)

    /**
     * Updates an existing user profile in the database.
     * @param user The updated user profile.
     * */
    suspend fun updateUserProfile(user: UserProfileEntity)

    /**
     * Retrieves a user profile from the database by user ID.
     *
     * @param userId The ID of the user profile to retrieve.
     */
    fun getUserProfile(userId: Int): Flow<UserProfileEntity?>
}