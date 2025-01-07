package com.example.githubapp.model.repository

import com.example.githubapp.model.remote_data_source.GitHubUser
import com.example.githubapp.model.remote_data_source.RemoteDataSource
import javax.inject.Inject

/**
 * [UserRepository]の実装クラス
 */
class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : UserRepository {
    override suspend fun getUser(userName: String): User {
        return remoteDataSource.getGitHubUser(userName).toUser()
    }

    private fun GitHubUser.toUser(): User {
        return User(
            id = UserId(id),
            name = name,
            avatarUrl = NetworkImage(Url(avatarUrl)),
            blogUrl = Url(blog),
        )
    }
}