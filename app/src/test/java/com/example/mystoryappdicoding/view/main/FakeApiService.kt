package com.example.mystoryappdicoding.view.main

import com.example.mystoryappdicoding.data.remote.ApiService
import com.example.mystoryappdicoding.data.remote.dto.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call

class FakeApiService: ApiService {
    override fun login(loginRequest: LoginRequest): Call<LoginResponse> {
        return Object()
    }


    override fun register(registerRequestDto: RegisterRequest): Call<RegisterResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getStories(page: Int, limit: Int, isWithLocation: Int): ListStoryResponse {
        TODO("Not yet implemented")
    }

    override fun addStory(
        description: RequestBody,
        image: MultipartBody.Part
    ): Call<AddNewStoryResponse> {
        TODO("Not yet implemented")
    }
}