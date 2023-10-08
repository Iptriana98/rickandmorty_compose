package com.iptrianaa.therickandmortywiki.config.network.api

import com.iptrianaa.therickandmortywiki.data.CharacterResult
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApi {
    @GET("character")
    suspend fun getCharacters(): Response<CharacterResult>
}