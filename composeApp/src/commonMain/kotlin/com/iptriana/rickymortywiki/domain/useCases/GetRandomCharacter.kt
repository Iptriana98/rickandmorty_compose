package com.iptriana.rickymortywiki.domain.useCases

import com.iptriana.rickymortywiki.domain.Repository
import com.iptriana.rickymortywiki.domain.model.CharacterModel
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GetRandomCharacter(private val repository: Repository) {
    private val randomId = (1..826).random()
    suspend operator fun invoke(): CharacterModel {
        repository.getCharacterDB()
        return repository.getSingleCharacter(randomId.toString())
    }

    private fun getCurrentDayOfTheYear(): String {
        val instant = Clock.System.now()
        val localTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return "${localTime.dayOfYear}${localTime.year}"
    }
}