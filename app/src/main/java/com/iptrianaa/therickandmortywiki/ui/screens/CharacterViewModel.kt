package com.iptrianaa.therickandmortywiki.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.iptrianaa.therickandmortywiki.data.remote.datasources.character.Character
import com.iptrianaa.therickandmortywiki.data.remote.repository.CharacterRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CharacterViewModel(private val repo: CharacterRepo) : ViewModel() {

    val characters: Flow<PagingData<Character>> = repo.getCharacters()
}