package com.iptrianaa.therickandmortywiki.data.config

import com.iptrianaa.therickandmortywiki.data.remote.datasources.character.CharacterResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface used to connect to the API Rick and Morty
 * API Documentation: https://rickandmortyapi.com/documentation
 * Copyright (c) 2023 by iptrianaa.
 */
interface CharacterService {
    @GET("api/character/")
    suspend fun getCharacters(@Query("page") page: Int): CharacterResult
}