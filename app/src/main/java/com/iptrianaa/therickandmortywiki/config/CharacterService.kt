package com.iptrianaa.therickandmortywiki.config

import com.iptrianaa.therickandmortywiki.data.CharacterResult
import retrofit2.http.GET

/**
 * Interface used to connect to the API Rick and Morty
 * API Documentation: https://rickandmortyapi.com/documentation
 * Copyright (c) 2023 by iptrianaa.
 */
interface CharacterService {
    @GET("https://rickandmortyapi.com/api/character")
    suspend fun getCharacters(): CharacterResult
}