package com.iptriana.rickymortywiki.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterModel(
    val id: Int,
    val isAlive: Boolean,
    val name: String,
    val image: String,
    val species: String,
    val gender: String,
    val origin: String,
    val episodes: List<String>
)