package com.iptriana.rickymortywiki.ui.detail

import com.iptriana.rickymortywiki.domain.model.CharacterModel
import com.iptriana.rickymortywiki.domain.model.EpisodeModel

data class CharacterDetailState(
    val characterModel: CharacterModel,
    val episodes: List< EpisodeModel>? = null
)
