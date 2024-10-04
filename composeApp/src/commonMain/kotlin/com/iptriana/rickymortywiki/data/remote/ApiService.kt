package com.iptriana.rickymortywiki.data.remote

import com.iptriana.rickymortywiki.data.remote.response.CharacterResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(private val client: HttpClient){
    suspend fun getSingleCharacter(id: String): CharacterResponse = client.get("api/character/$id").body()
//    suspend fun getCharacters() = client.getCharacters()
}