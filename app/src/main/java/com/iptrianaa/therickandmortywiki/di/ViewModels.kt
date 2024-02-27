package com.iptrianaa.therickandmortywiki.di

import com.iptrianaa.therickandmortywiki.ui.screens.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::CharacterViewModel)
}