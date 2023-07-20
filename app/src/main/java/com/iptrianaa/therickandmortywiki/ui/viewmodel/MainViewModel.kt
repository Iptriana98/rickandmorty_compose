package com.iptrianaa.therickandmortywiki.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iptrianaa.therickandmortywiki.config.network.api.NetworkApi
import com.iptrianaa.therickandmortywiki.data.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val networkApi: NetworkApi): ViewModel() {
    private val _characters: MutableLiveData<List<Character>> = MutableLiveData()
    val characters: LiveData<List<Character>> = _characters

    fun getCharacters() = viewModelScope.launch {
        try {
            val response = networkApi.getCharacters()
            Log.i("TAG", "getCharacters: ${response.body()}")
            if (response.isSuccessful){
                _characters.value = response.body()!!.results
            }else Log.i("TAG", "getCharacters: ${response.code()}")
        }catch (e: Exception){ Log.i("TAG", "getCharacters: ${e.message}") }
    }
}