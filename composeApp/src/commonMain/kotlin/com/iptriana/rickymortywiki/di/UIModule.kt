package com.iptriana.rickymortywiki.di

import com.iptriana.rickymortywiki.ui.home.tabs.characters.CharactersViewModel
import com.iptriana.rickymortywiki.ui.home.tabs.episodes.EpisodesViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::EpisodesViewModel)
    viewModelOf(::CharactersViewModel)
}