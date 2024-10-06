package com.iptriana.rickymortywiki.data

import com.iptriana.rickymortywiki.data.remote.ApiService
import com.iptriana.rickymortywiki.domain.Repository
import com.iptriana.rickymortywiki.domain.model.CharacterModel

class RepositoryImpl(private val api: ApiService) : Repository {
    override suspend fun getSingleCharacter(id: String): CharacterModel =
        api.getSingleCharacter(id).toDomain()
}