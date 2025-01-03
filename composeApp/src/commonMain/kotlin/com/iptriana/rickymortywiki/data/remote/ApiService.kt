package com.iptriana.rickymortywiki.data.remote

import com.iptriana.rickymortywiki.data.remote.response.CharacterResponse
import com.iptriana.rickymortywiki.data.remote.response.CharacterWrapperResponse
import com.iptriana.rickymortywiki.data.remote.response.EpisodesWrapperResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ApiService(private val client: HttpClient) {
    suspend fun getSingleCharacter(id: String): CharacterResponse =
        client.get("api/character/$id").body()

    suspend fun getCharacters(page: Int): CharacterWrapperResponse = client.get("api/character/") {
        parameter("page", page)
    }.body()

    suspend fun getAllEpisodes(page: Int): EpisodesWrapperResponse = client.get("api/episode"){
        parameter("page", page)
    }.body()
}