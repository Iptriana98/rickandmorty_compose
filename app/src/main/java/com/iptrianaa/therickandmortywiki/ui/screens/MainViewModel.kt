package com.iptrianaa.therickandmortywiki.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iptrianaa.therickandmortywiki.config.CharacterService
import com.iptrianaa.therickandmortywiki.data.Character
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {

    private val _state = MutableLiveData(UIState())
        val state: LiveData<UIState> = _state

    init {
        viewModelScope.launch {
            _state.value = UIState(
                Retrofit.Builder()
                    .baseUrl("https://rickandmortyapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(CharacterService::class.java)
                    .getCharacters()
                    .results
            )
        }
    }

    data class UIState(
        val characters: List<Character> = emptyList(),
        val isLoading: Boolean = false
    )
}