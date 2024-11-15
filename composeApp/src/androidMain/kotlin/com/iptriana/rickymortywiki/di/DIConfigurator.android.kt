package com.iptriana.rickymortywiki.di

import com.iptriana.rickymortywiki.data.database.RickyMortyDatabase
import com.iptriana.rickymortywiki.data.database.getDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module {
    return module{
        single<RickyMortyDatabase> { getDatabase(get()) }
    }
}