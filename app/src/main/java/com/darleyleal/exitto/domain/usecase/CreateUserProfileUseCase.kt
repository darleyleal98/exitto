package com.darleyleal.exitto.domain.usecase

import android.util.Log
import com.darleyleal.exitto.domain.repository.UserProfileRepository
import com.darleyleal.exitto.domain.utils.buildUserProfileEntity
import com.darleyleal.exitto.domain.utils.validateUserProfile
import javax.inject.Inject

/**
 *Use case to save user profile data in locadatabase
 * */
class CreateUserProfileUseCase @Inject constructor(private val repository: UserProfileRepository) {

    /**
     * Saves user profile data in local database.
     * */
    suspend operator fun invoke(
        name: String?,
        dateOfBirthday: String?,
        sex: String?,
        imagePath: String? = null,
        heightCm: Double?,
        weightKg: Double?,
    ) {
        Log.d("CreateUserProfileUseCase", "Creating user profile with name=$name")

        validateUserProfile(
            name = name, imagePath = imagePath,
            dateOfBirthday = dateOfBirthday, sex = sex,
            heightCm = heightCm, weightKg = weightKg, strict = false
        )

        repository.createUserProfile(
            buildUserProfileEntity(
                name = name, imagePath = imagePath,
                dateOfBirthday = dateOfBirthday, sex = sex,
                heightCm = heightCm, weightKg = weightKg
            )
        )
    }
}