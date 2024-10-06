package com.iptriana.rickymortywiki.di

import com.iptriana.rickymortywiki.domain.useCases.GetRandomCharacter
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetRandomCharacter)
}