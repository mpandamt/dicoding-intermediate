package com.example.mystoryappdicoding.data

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.mystoryappdicoding.data.remote.dto.Story

interface StoryRepository {
    fun listStory(): LiveData<PagingData<Story>>
}