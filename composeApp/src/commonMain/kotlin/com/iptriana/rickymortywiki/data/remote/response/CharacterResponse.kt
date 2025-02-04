package com.iptriana.rickymortywiki.data.remote.response

import com.iptriana.rickymortywiki.domain.model.CharacterModel
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val origin: OriginResponse,
    val episode: List<String>,
){
    fun toDomain() = CharacterModel(
        id = id,
        name = name,
        isAlive = status == "Alive",
        image = image,
        species = species,
        gender = gender,
        origin = origin.name,
        episodes = episode.map { it.substringAfterLast("/") }
    )
}
