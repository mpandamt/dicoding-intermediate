package com.example.mystoryappdicoding.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mystoryappdicoding.data.remote.ApiService
import com.example.mystoryappdicoding.data.remote.NETWORK_PAGE_SIZE
import com.example.mystoryappdicoding.data.remote.StoryPagingSource
import com.example.mystoryappdicoding.data.remote.dto.Story
import com.example.mystoryappdicoding.model.UserModel
import com.example.mystoryappdicoding.model.UserPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(private val pref: UserPreference, private val apiService: ApiService) : ViewModel() {
    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }

    fun fetchStory(): Flow<PagingData<Story>> {
        return getStoriesStream().cachedIn(viewModelScope)
    }

    private fun getStoriesStream(): Flow<PagingData<Story>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { StoryPagingSource(apiService) }
        ).flow
    }

}