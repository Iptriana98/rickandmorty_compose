package com.iptriana.rickymortywiki.domain.useCases

import com.iptriana.rickymortywiki.domain.Repository
import com.iptriana.rickymortywiki.domain.model.CharacterModel
import com.iptriana.rickymortywiki.domain.model.CharacterOfTheDayModel
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GetRandomCharacter(private val repository: Repository) {
    suspend operator fun invoke(): CharacterModel {
        val characterDatabase: CharacterOfTheDayModel? = repository.getCharacterDB()
        val selectedDate = getCurrentDayOfTheYear()

        return if (characterDatabase != null && selectedDate == characterDatabase.selectedDate) {
            characterDatabase.characterModel
        } else {
            val result = generateRandomCharacter()

            repository.saveCharacterOfTheDay(
                CharacterOfTheDayModel(
                    characterModel = result,
                    selectedDate = selectedDate
                )
            )
            return result
        }
    }

    private suspend fun generateRandomCharacter(): CharacterModel {
        val randomId = (1..826).random()
        return repository.getSingleCharacter(randomId.toString())
    }

    private fun getCurrentDayOfTheYear(): String {
        val instant = Clock.System.now()
        val localTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return "${localTime.dayOfYear}${localTime.year}"
    }
}