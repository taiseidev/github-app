package com.example.githubapp.model.remote_data_source

/**
 * サーバからのレスポンスを取り出すdata Sourceのインターフェース
 */
interface RemoteDataSource {
    /**
     * サーバからのレスポンスを[GitHubUser]として返す
     */
    suspend fun getGitHubUser(userName: String): GitHubUser
}