package com.iptriana.rickymortywiki.data

import androidx.paging.PagingConfig
import app.cash.paging.Pager
import app.cash.paging.PagingData
import com.iptriana.rickymortywiki.data.remote.ApiService
import com.iptriana.rickymortywiki.data.remote.paging.CharacterPagingSource
import com.iptriana.rickymortywiki.domain.Repository
import com.iptriana.rickymortywiki.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val api: ApiService, private val characterPagingSource: CharacterPagingSource
) : Repository {

    companion object {
        const val MAX_ITEMS = 20
        const val PREFETCH_DISTANCE = 5
    }

    override suspend fun getSingleCharacter(id: String): CharacterModel =
        api.getSingleCharacter(id).toDomain()

    override fun getAllCharacters(): Flow<PagingData<CharacterModel>> =
        Pager(config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH_DISTANCE),
            pagingSourceFactory = { characterPagingSource }).flow

}