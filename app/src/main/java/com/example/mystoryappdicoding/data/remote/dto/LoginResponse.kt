package com.example.mystoryappdicoding.data.remote.dto

data class LoginResponse(
    val error: Boolean,
    val loginResult: LoginResult,
    val message: String
)