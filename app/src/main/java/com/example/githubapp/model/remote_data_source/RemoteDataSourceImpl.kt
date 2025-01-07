package com.example.githubapp.model.remote_data_source

import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiClient: ApiClient
) : RemoteDataSource {
    /**
     * ここでResponse<GitHubUser>ではなくGitHubUserを返している理由としては
     * Retrofitの依存をDataSourceで留めておくため
     */
    override suspend fun getGitHubUser(userName: String): GitHubUser {
        val response = apiClient.getGitHubUser(username = userName)
        if (response.isSuccessful) {
            val gitHubUser: GitHubUser = requireNotNull(response.body())
            return gitHubUser
        } else {
            throw HttpException()
        }
    }
}

/**
 * ResponseがisSuccessful != trueだった時に
 * 投げられる例外
 */
class HttpException : Throwable()