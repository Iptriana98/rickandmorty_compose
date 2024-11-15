package com.iptriana.rickymortywiki.domain

import androidx.paging.PagingData
import com.iptriana.rickymortywiki.domain.model.CharacterModel
import com.iptriana.rickymortywiki.domain.model.CharacterOfTheDayModel
import kotlinx.coroutines.flow.Flow


interface Repository {
    suspend fun getSingleCharacter(id: String): CharacterModel
    fun getAllCharacters(): Flow<PagingData<CharacterModel>>
    suspend fun getCharacterDB(): CharacterOfTheDayModel?
    suspend fun saveCharacterOfTheDay(character: CharacterOfTheDayModel)
}