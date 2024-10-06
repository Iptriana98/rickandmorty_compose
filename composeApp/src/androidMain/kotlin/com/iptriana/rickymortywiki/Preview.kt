package com.iptriana.rickymortywiki

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.iptriana.rickymortywiki.domain.model.CharacterModel
import com.iptriana.rickymortywiki.ui.home.tabs.characters.CharacterOfTheDay

class Preview {
    @Preview
    @Composable
    fun PreviewApp() {
        CharacterOfTheDay(CharacterModel(
            id = 1,
            name = "Rick Sanchez",
            isAlive = true,
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
        ))
    }
}