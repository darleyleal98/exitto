package com.darleyleal.exitto.domain.usecase

import com.darleyleal.exitto.domain.repository.UserProfileRepository
import javax.inject.Inject

/**
 * Use case to retrieve user profile data from local database.
 * */
class GetUserProfileUseCase @Inject constructor(private val repository: UserProfileRepository) {
    /**
     * Retrieves user profile data from local database.
     **/
    operator fun invoke(userId: Int) = repository.getUserProfile(userId)
}