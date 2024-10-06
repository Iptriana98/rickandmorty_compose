package com.iptriana.rickymortywiki.domain.model

data class CharacterModel (
    val id: Int,
    val isAlive: Boolean,
    val name: String,
    val image: String
)