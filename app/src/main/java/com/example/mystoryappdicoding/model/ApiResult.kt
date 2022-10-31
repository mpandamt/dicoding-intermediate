package com.example.mystoryappdicoding.model

data class ApiResult(
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val message: String = ""
)