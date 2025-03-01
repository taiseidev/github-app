package com.example.githubapp.model.remote_data_source

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * [RemoteDataSource]のModule
 */
@Module
@InstallIn(ViewModelComponent::class)
class RemoteDataSourceModule {
    /**
     * [RemoteDataSource]のDIに用いられる
     * インスタンスを生成して返す
     */
    @Provides
    fun provideRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource {
        return remoteDataSourceImpl
    }

}