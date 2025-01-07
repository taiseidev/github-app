package com.example.githubapp.model.repository

data class User(
    val id: UserId,
    val name: String,
    val avatarUrl: NetworkImage,
    val blogUrl: Url,
)
