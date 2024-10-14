package com.iptriana.rickymortywiki.domain

import androidx.paging.PagingData
import com.iptriana.rickymortywiki.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow


interface Repository {
    suspend fun getSingleCharacter(id: String): CharacterModel
    fun getAllCharacters(): Flow<PagingData<CharacterModel>>
}