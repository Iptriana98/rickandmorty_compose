package com.iptrianaa.therickandmortywiki.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iptrianaa.therickandmortywiki.data.config.RetrofitClient
import com.iptrianaa.therickandmortywiki.data.remote.character.Character
import kotlinx.coroutines.launch

class MainViewModel(private val client: RetrofitClient = RetrofitClient) : ViewModel() {

    private val _state = MutableLiveData(UIState())
        val state: LiveData<UIState> = _state

    init {
        viewModelScope.launch {
            _state.value = UIState(
                client.api.getCharacters().results
            )
        }
    }

    data class UIState(
        val characters: List<Character> = emptyList(),
        val isLoading: Boolean = false
    )
}