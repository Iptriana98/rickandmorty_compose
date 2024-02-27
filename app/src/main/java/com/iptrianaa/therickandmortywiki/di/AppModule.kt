package com.iptrianaa.therickandmortywiki.di

import com.iptrianaa.therickandmortywiki.di.Qualifiers.BASE_URL
import com.iptrianaa.therickandmortywiki.di.Qualifiers.BUILD_TYPE
import org.koin.android.BuildConfig
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Example of module for koin
 */
val appModule = module {
    single(named(BUILD_TYPE)) { BuildConfig.BUILD_TYPE }
    single(named(BASE_URL)) { "https://rickandmortyapi.com/" }
}

enum class Qualifiers{
    BASE_URL,
    BUILD_TYPE
}