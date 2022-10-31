package com.example.mystoryappdicoding.data.remote.dto


data class ListStoryResponse(
    val error: Boolean,
    val listStory: List<Story>,
    val message: String
)