package com.darleyleal.exitto.domain.usecase

import com.darleyleal.exitto.domain.repository.UserProfileRepository
import com.darleyleal.exitto.domain.utils.buildUserProfileEntity
import com.darleyleal.exitto.domain.utils.validateUserProfile
import javax.inject.Inject

/**
 * Use case to retrieve user profile data from local database.
 **/
class UpdateUserProfileUseCase @Inject constructor(private val repository: UserProfileRepository) {
    /**
     * Updates user profile data in local database.
     **/
    suspend operator fun invoke(
        name: String?,
        dateOfBirthday: String?,
        sex: String?,
        imagePath: String?,
        heightCm: Double?,
        weightKg: Double?,
    ) {
        validateUserProfile(
            name = name, imagePath = imagePath,
            dateOfBirthday = dateOfBirthday, sex = sex,
            heightCm = heightCm, weightKg = weightKg,
        )

        repository.updateUserProfile(buildUserProfileEntity(
            name = name, imagePath = imagePath,
            dateOfBirthday = dateOfBirthday, sex = sex,
            heightCm = heightCm, weightKg = weightKg,
        ))
    }
}