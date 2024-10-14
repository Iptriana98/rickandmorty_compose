package com.iptriana.rickymortywiki.data.remote.paging

import androidx.paging.PagingState
import app.cash.paging.PagingSource
import com.iptriana.rickymortywiki.data.remote.ApiService
import com.iptriana.rickymortywiki.domain.model.CharacterModel
import io.ktor.utils.io.errors.IOException

class CharacterPagingSource(private val api: ApiService) : PagingSource<Int, CharacterModel>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> =
        try {
            val page = params.key ?: 1
            val response = api.getCharacters(page)
            val characters = response.results

            val prev = if (page > 0) -1 else null
            val next = if (response.info.next != null) page + 1 else null

            LoadResult.Page(data = characters.map { it.toDomain() }, prevKey = prev, nextKey = next)
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
}