package com.example.mystoryappdicoding.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mystoryappdicoding.data.remote.dto.Story
import retrofit2.awaitResponse

const val NETWORK_PAGE_SIZE = 10
class StoryPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Story>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Story> {
        try {
            val position = params.key ?: 1
            val response = apiService.getStories(
                page = position,
                limit = params.loadSize
            )

            val nextKey = if (response.listStory.isEmpty()) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }

            return LoadResult.Page(
                data = response.listStory,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Story>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}