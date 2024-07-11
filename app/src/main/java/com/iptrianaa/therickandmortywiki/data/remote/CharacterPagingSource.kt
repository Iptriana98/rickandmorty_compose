package com.iptrianaa.therickandmortywiki.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.iptrianaa.therickandmortywiki.data.config.CharacterService
import com.iptrianaa.therickandmortywiki.data.remote.datasources.character.Character

class CharacterPagingSource (private val service: CharacterService): PagingSource<Int, Character>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val nextPage = params.key ?: 1
            val response = service.getCharacters(nextPage)
            LoadResult.Page(
                data = response.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (response.results.isEmpty()) null else nextPage + 1
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? = state.anchorPosition
}