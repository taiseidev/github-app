package com.example.githubapp.model.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * [UserRepository]のModule
 */
@Module
@InstallIn(ViewModelComponent::class)
class UserRepositoryModule {

    @Provides
    fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository {
        return userRepositoryImpl
    }

}