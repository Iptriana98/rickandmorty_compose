package com.iptrianaa.therickandmortywiki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iptrianaa.therickandmortywiki.config.CharacterService
import com.iptrianaa.therickandmortywiki.data.Character
import com.iptrianaa.therickandmortywiki.ui.composables.CharacterCard
import com.iptrianaa.therickandmortywiki.ui.theme.TheRickAndMortyWikiTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheRickAndMortyWikiTheme {
                val characters = produceState<List<Character>>(initialValue = emptyList()) {
                    value = Retrofit.Builder().baseUrl("https://rickandmortyapi.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(CharacterService::class.java)
                        .getCharacters()
                        .results
                }
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(120.dp),
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        items(characters.value) {character ->
                            CharacterCard(character = character)
                        }
                    }
                }
            }
        }
    }
}