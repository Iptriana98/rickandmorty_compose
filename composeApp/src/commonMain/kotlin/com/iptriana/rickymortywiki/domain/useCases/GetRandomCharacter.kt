package com.iptriana.rickymortywiki.domain.useCases

import com.iptriana.rickymortywiki.domain.Repository
import com.iptriana.rickymortywiki.domain.model.CharacterModel

class GetRandomCharacter(private val repository: Repository) {
    private val randomId = (1..826).random()
    suspend operator fun invoke(): CharacterModel = repository.getSingleCharacter(randomId.toString())
}