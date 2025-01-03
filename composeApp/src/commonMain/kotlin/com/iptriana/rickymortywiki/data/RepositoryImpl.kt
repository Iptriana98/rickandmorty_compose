package com.iptriana.rickymortywiki.data

import androidx.paging.PagingConfig
import app.cash.paging.Pager
import app.cash.paging.PagingData
import com.iptriana.rickymortywiki.data.database.RickyMortyDatabase
import com.iptriana.rickymortywiki.data.remote.ApiService
import com.iptriana.rickymortywiki.data.remote.paging.CharacterPagingSource
import com.iptriana.rickymortywiki.data.remote.paging.EpisodesPagingSource
import com.iptriana.rickymortywiki.domain.Repository
import com.iptriana.rickymortywiki.domain.model.CharacterModel
import com.iptriana.rickymortywiki.domain.model.CharacterOfTheDayModel
import com.iptriana.rickymortywiki.domain.model.EpisodeModel
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val api: ApiService,
    private val characterPagingSource: CharacterPagingSource,
    private val episodesPagingSource: EpisodesPagingSource,
    private val database: RickyMortyDatabase
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

    override fun getAllEpisodes(): Flow<androidx.paging.PagingData<EpisodeModel>> =
        Pager(config = PagingConfig(pageSize = MAX_ITEMS, prefetchDistance = PREFETCH_DISTANCE),
            pagingSourceFactory = { episodesPagingSource }).flow

    override suspend fun getCharacterDB(): CharacterOfTheDayModel? =
        database.getPreferencesDao().getCharacterOfTheDay()?.toDomain()

    override suspend fun saveCharacterOfTheDay(character: CharacterOfTheDayModel) {
        database.getPreferencesDao().saveCharacterOfTheDay(character.toEntity())
    }


}