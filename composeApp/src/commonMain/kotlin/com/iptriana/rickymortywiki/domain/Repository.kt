package com.iptriana.rickymortywiki.domain

import androidx.paging.PagingData
import com.iptriana.rickymortywiki.domain.model.CharacterModel
import com.iptriana.rickymortywiki.domain.model.CharacterOfTheDayModel
import com.iptriana.rickymortywiki.domain.model.EpisodeModel
import kotlinx.coroutines.flow.Flow


interface Repository {
    suspend fun getSingleCharacter(id: String): CharacterModel
    fun getAllCharacters(): Flow<PagingData<CharacterModel>>
    fun getAllEpisodes(): Flow<PagingData<EpisodeModel>>
    suspend fun getCharacterDB(): CharacterOfTheDayModel?
    suspend fun saveCharacterOfTheDay(character: CharacterOfTheDayModel)
    suspend fun getEpisodesForCharacter(episodes: List<String>): List<EpisodeModel>

}