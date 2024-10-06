package com.iptriana.rickymortywiki.ui.home.tabs.characters

import com.iptriana.rickymortywiki.domain.model.CharacterModel

data class CharacterState(
    val characterOfTheDay: CharacterModel? = null,
)