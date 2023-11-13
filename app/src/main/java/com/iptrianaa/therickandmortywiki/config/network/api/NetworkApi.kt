package com.iptrianaa.therickandmortywiki.config.network.api

import com.iptrianaa.therickandmortywiki.data.CharacterResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): Response<CharacterResult>
}