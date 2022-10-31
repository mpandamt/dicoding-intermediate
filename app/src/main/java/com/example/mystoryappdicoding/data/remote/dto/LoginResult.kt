package com.example.mystoryappdicoding.data.remote.dto

data class LoginResult(
    val name: String,
    val token: String,
    val userId: String
)