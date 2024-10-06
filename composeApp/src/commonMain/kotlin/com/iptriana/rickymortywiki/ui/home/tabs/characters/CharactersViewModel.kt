package com.iptriana.rickymortywiki.ui.home.tabs.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iptriana.rickymortywiki.domain.model.CharacterModel
import com.iptriana.rickymortywiki.domain.useCases.GetRandomCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersViewModel(private val getRandomCharacter: GetRandomCharacter): ViewModel() {
    private val _state: MutableStateFlow<CharacterState> = MutableStateFlow(CharacterState())
    val state:StateFlow<CharacterState> = _state

    init {
        viewModelScope.launch{
            val result: CharacterModel = withContext(Dispatchers.IO){
                getRandomCharacter()
            }
            _state.update { it.copy(characterOfTheDay = result) }
        }
    }
}