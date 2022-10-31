package com.example.mystoryappdicoding.view.maps

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystoryappdicoding.data.remote.ApiService
import com.example.mystoryappdicoding.data.remote.dto.LoginResponse
import com.example.mystoryappdicoding.data.remote.dto.RegisterRequest
import com.example.mystoryappdicoding.data.remote.dto.RegisterResponse
import com.example.mystoryappdicoding.data.remote.dto.Story
import com.example.mystoryappdicoding.model.ApiResult
import com.example.mystoryappdicoding.model.UserPreference
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsViewModel(private val apiService: ApiService) : ViewModel() {
    private val _storyResult = MutableLiveData<List<Story>>()
    val story = _storyResult

    fun getStory() {
        viewModelScope.launch {
            val result = apiService.getStories(1, 50,1)
            if (!result.error) {
                _storyResult.value = result.listStory
            }
        }
    }
}