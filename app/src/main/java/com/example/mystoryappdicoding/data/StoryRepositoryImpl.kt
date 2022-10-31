package com.example.mystoryappdicoding.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.mystoryappdicoding.data.remote.ApiService
import com.example.mystoryappdicoding.data.remote.NETWORK_PAGE_SIZE
import com.example.mystoryappdicoding.data.remote.StoryPagingSource
import com.example.mystoryappdicoding.data.remote.dto.Story

class StoryRepositoryImpl(
    private val apiService: ApiService
): StoryRepository {
    override fun listStory(): LiveData<PagingData<Story>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                StoryPagingSource(
                    apiService = apiService
                )
            }
        ).liveData
    }
}