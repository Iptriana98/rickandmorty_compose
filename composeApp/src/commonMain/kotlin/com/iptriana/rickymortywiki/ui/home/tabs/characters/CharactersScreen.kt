package com.iptriana.rickymortywiki.ui.home.tabs.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.iptriana.rickymortywiki.domain.model.CharacterModel
import com.iptriana.rickymortywiki.ui.core.ex.vertical
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharactersScreen() {
    val charactersViewModel = koinViewModel<CharactersViewModel>()
    val characterState by charactersViewModel.state.collectAsState()

    Column(Modifier.fillMaxSize()) {
        characterState.characterOfTheDay?.let {
            CharacterOfTheDay(characterModel = it)
        }
    }
}

@Composable
fun CharacterOfTheDay(characterModel: CharacterModel? = null) {
    Card(modifier = Modifier.fillMaxWidth().height(400.dp), shape = RoundedCornerShape(12)) {
        if (characterModel == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(Modifier.background(Color.Green))
            }
        } else {
            Box(contentAlignment = Alignment.BottomStart) {
                Box(modifier = Modifier.fillMaxSize().background(Color.Green.copy(alpha = 0.5f)))
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    model = characterModel.image
                )
                Box(
                    Modifier.fillMaxSize().background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.9f), // Inicio fuerte con opacidad alta
                                Color.Black.copy(alpha = 0.6f), // Transición suave
                                Color.Black.copy(alpha = 0.3f), // Transición más suave
                                Color.White.copy(alpha = 0f)    // Fin completamente transparente
                            ),
                            startX = 0f,
                            endX = 1000f // Ajuste el rango del gradiente para una transición más larga
                        )
                    )
                )

                Text(
                    characterModel.name,
                    fontSize = 40.sp,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                        .vertical()
                        .rotate(-90f)
                )
            }
        }
    }
}