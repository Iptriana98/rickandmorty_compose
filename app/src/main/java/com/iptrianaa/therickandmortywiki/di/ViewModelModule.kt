package com.iptrianaa.therickandmortywiki.di

import com.iptrianaa.therickandmortywiki.data.remote.repository.CharacterRepo
import com.iptrianaa.therickandmortywiki.ui.screens.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CharacterViewModel(get()) }
    single { CharacterRepo(get()) }
}