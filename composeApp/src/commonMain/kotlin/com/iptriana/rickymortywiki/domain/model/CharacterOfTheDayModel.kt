package com.iptriana.rickymortywiki.domain.model

import com.iptriana.rickymortywiki.data.database.entity.CharacterOfTheDayEntity

data class CharacterOfTheDayModel (
    val characterModel: CharacterModel,
    val selectedDate: String
) {
    fun toEntity(): CharacterOfTheDayEntity {
        return CharacterOfTheDayEntity(
            id = characterModel.id,
            isAlive = characterModel.isAlive,
            name = characterModel.name,
            image = characterModel.image,
            selectedDate = selectedDate
        )
    }
}