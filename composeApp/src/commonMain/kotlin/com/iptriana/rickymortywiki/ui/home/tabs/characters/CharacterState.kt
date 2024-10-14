package com.iptriana.rickymortywiki.ui.home.tabs.characters

import app.cash.paging.PagingData
import com.iptriana.rickymortywiki.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class CharacterState(
    val characterOfTheDay: CharacterModel? = null,
    val characters: Flow<PagingData<CharacterModel>> = emptyFlow()
)