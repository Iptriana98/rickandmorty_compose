package com.iptriana.rickymortywiki.ui.detail

import androidx.lifecycle.ViewModel
import com.iptriana.rickymortywiki.domain.model.CharacterModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CharacterDetailViewModel(characterModel: CharacterModel): ViewModel() {
    private val _uiState = MutableStateFlow<CharacterDetailState>(CharacterDetailState(characterModel))
    val uiState:StateFlow<CharacterDetailState> = _uiState

}