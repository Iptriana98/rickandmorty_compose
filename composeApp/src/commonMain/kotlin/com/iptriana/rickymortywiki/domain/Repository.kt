package com.iptriana.rickymortywiki.domain

import com.iptriana.rickymortywiki.domain.model.CharacterModel


interface Repository {
    suspend fun getSingleCharacter(id: String): CharacterModel
}