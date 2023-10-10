package com.iptrianaa.therickandmortywiki.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.iptrianaa.therickandmortywiki.config.datasource.ApiDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val apiDataSource: ApiDataSource): ViewModel() {
    val pager = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { apiDataSource },
    ).flow.cachedIn(viewModelScope)

//    fun getCharacters() = viewModelScope.launch {
//        try {
//            val response = networkApi.getCharacters()
//            Log.i("TAG", "getCharacters: ${response.body()}")
//            if (response.isSuccessful){
//                _characters.value = response.body()!!.results
//            }else Log.i("TAG", "getCharacters: ${response.code()}")
//        }catch (e: Exception){ Log.i("TAG", "getCharacters: ${e.message}") }
//    }
}