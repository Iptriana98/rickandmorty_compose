package com.iptrianaa.therickandmortywiki.config.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.iptrianaa.therickandmortywiki.config.network.api.NetworkApi
import com.iptrianaa.therickandmortywiki.data.Character
import javax.inject.Inject

class ApiDataSource @Inject constructor(private val networkApi: NetworkApi): PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val page = params.key ?: 1
            val response = networkApi.getCharacters()

            LoadResult.Page(
                data = response.body()!!.results,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.body()!!.results.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}