package com.iptriana.rickymortywiki.domain.useCases

import com.iptriana.rickymortywiki.domain.Repository

class GetRandomCharacter(private val repository: Repository) {
    private val randomId = (1..826).random()
    suspend operator fun invoke() = repository.getSingleCharacter(randomId.toString())
}