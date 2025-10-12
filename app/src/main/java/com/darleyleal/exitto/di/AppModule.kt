package com.darleyleal.exitto.di

import android.content.Context
import androidx.room.Room
import com.darleyleal.exitto.data.dao.UserDao
import com.darleyleal.exitto.data.database.AppDatabase
import com.darleyleal.exitto.data.datastore.LoginPreferences
import com.darleyleal.exitto.data.repository.AuthRepositoryImpl
import com.darleyleal.exitto.domain.repository.AuthRepository
import com.darleyleal.exitto.presentation.utils.GoogleSignInHelper
import com.darleyleal.exitto.domain.usecase.CheckAuthStatusUseCase
import com.darleyleal.exitto.domain.usecase.GoogleSignInUseCase
import com.darleyleal.exitto.domain.usecase.RegisterUserUseCase
import com.darleyleal.exitto.domain.usecase.ValidateRegisterFormUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "app_database"
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideLoginPreferences(@ApplicationContext context: Context): LoginPreferences = LoginPreferences(context)

    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth,
        loginPreferences: LoginPreferences
    ): AuthRepository = AuthRepositoryImpl(firebaseAuth, loginPreferences)

    @Provides
    fun provideRegisterUserUseCase(authRepository: AuthRepository): RegisterUserUseCase =
        RegisterUserUseCase(authRepository)

    @Provides
    fun provideValidateRegisterFormUseCase(): ValidateRegisterFormUseCase =
        ValidateRegisterFormUseCase()

    @Provides
    fun provideCheckAuthStatusUseCase(
        authRepository: AuthRepository,
        loginPreferences: LoginPreferences
    ): CheckAuthStatusUseCase = CheckAuthStatusUseCase(authRepository, loginPreferences)

    @Provides
    fun provideGoogleSignInUseCase(authRepository: AuthRepository): GoogleSignInUseCase =
        GoogleSignInUseCase(authRepository)

    @Provides
    fun provideGoogleSignInHelper(@ApplicationContext context: Context): GoogleSignInHelper =
        GoogleSignInHelper(context)
}