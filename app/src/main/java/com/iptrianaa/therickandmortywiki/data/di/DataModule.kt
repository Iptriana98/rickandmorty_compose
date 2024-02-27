package com.iptrianaa.therickandmortywiki.data.di

import com.iptrianaa.therickandmortywiki.data.config.CharacterService
import com.iptrianaa.therickandmortywiki.data.remote.repository.CharacterRepo
import com.iptrianaa.therickandmortywiki.di.Qualifiers
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named(Qualifiers.BASE_URL)))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(CharacterService::class.java) }

    factoryOf(::CharacterRepo)
}