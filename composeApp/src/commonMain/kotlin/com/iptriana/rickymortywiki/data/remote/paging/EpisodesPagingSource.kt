package com.iptriana.rickymortywiki.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.iptriana.rickymortywiki.data.remote.ApiService
import com.iptriana.rickymortywiki.domain.model.EpisodeModel
import io.ktor.utils.io.errors.IOException

class EpisodesPagingSource(private val api: ApiService) : PagingSource<Int, EpisodeModel>() {
    override fun getRefreshKey(state: PagingState<Int, EpisodeModel>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeModel> = try {
        val page = params.key ?: 1
        val response = api.getAllEpisodes(page)
        val episodes = response.results.map { it.toDomain() }
        val prevKey = if (page > 1) page - 1 else null
        val nextKey = if (episodes.isNotEmpty()) page + 1 else null
        LoadResult.Page(data = episodes, prevKey = prevKey, nextKey = nextKey)
    } catch (e: IOException) {
        LoadResult.Error(e)
    }
}