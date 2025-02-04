package com.iptriana.rickymortywiki.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iptriana.rickymortywiki.domain.model.CharacterModel
import com.iptriana.rickymortywiki.domain.model.CharacterOfTheDayModel
import kotlinx.serialization.json.Json

@Entity(tableName = "character_of_the_day")
data class CharacterOfTheDayEntity(
    @PrimaryKey val id: Int,
    val isAlive: Boolean,
    val name: String,
    val image: String,
    val selectedDate: String,
    val species: String,
    val gender: String,
    val origin: String,
    val episodes: String
) {
    fun toDomain(): CharacterOfTheDayModel? {
        return CharacterOfTheDayModel(
            characterModel = CharacterModel(
                id = id,
                isAlive = isAlive,
                name = name,
                image = image,
                species = species,
                gender = gender,
                origin = origin,
                episodes = Json.decodeFromString<List<String>>(episodes)
            ),
            selectedDate = selectedDate)
    }
}
