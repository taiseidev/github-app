package com.example.githubapp.model.remote_data_source

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Singleton

/**
 * Retrofitを用いたGitHub APIのクライアントを生成するModule
 */
@Module
@InstallIn(SingletonComponent::class)
class ApiClientModule {
    /**
     * [ApiClient]のDIに用いられるインスタンスを生成
     */
    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideApiClient(apiClientProvider: ApiClientProvider): ApiClient {
        return apiClientProvider.provide()
    }

}