package com.example.mystoryappdicoding.data.remote

import com.example.mystoryappdicoding.data.remote.dto.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("register")
    fun register(@Body registerRequestDto: RegisterRequest): Call<RegisterResponse>

    @GET("stories")
    suspend fun getStories(
        @Query("page") page: Int,
        @Query("size") limit: Int,
        @Query("location") isWithLocation: Int = 1
    ) : ListStoryResponse

    @Multipart
    @POST("stories")
    fun addStory(
        @Part("description") description: RequestBody,
        @Part image: MultipartBody.Part
    ): Call<AddNewStoryResponse>

}