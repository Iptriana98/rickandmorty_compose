package com.iptrianaa.therickandmortywiki.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.iptrianaa.therickandmortywiki.data.config.CharacterService
import com.iptrianaa.therickandmortywiki.data.remote.CharacterPagingSource
import com.iptrianaa.therickandmortywiki.data.remote.datasources.character.Character
import kotlinx.coroutines.flow.Flow

class CharacterRepo(private val service: CharacterService) {

    companion object {
        const val MAX_ITEMS = 10
        const val PREFETCH_ITEMS = 3
    }
    fun getCharacters(): Flow<PagingData<Character>>{
        return Pager(
            config = PagingConfig(
                pageSize = MAX_ITEMS,
                enablePlaceholders = true,
                prefetchDistance = PREFETCH_ITEMS
            ),
            pagingSourceFactory = { CharacterPagingSource(service) }).flow
    }
}