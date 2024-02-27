package com.iptrianaa.therickandmortywiki.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iptrianaa.therickandmortywiki.data.remote.datasources.character.Character
import com.iptrianaa.therickandmortywiki.data.remote.repository.CharacterRepo
import kotlinx.coroutines.launch

class CharacterViewModel(private val repo: CharacterRepo) : ViewModel() {

    private val _state = MutableLiveData(UIState())
        val state: LiveData<UIState> = _state

    init {
        viewModelScope.launch {
            _state.value = UIState(
                repo.getCharacters()
            )
        }
    }

    data class UIState(
        val characters: List<Character> = emptyList(),
        val isLoading: Boolean = false
    )
}