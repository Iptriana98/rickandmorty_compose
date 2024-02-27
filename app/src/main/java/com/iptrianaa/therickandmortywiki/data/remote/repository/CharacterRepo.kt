package com.iptrianaa.therickandmortywiki.data.remote.repository

import com.iptrianaa.therickandmortywiki.data.config.CharacterService
import com.iptrianaa.therickandmortywiki.data.remote.datasources.character.Character

class CharacterRepo(private val service: CharacterService) {
    suspend fun getCharacters(): List<Character> = service.getCharacters().results
}