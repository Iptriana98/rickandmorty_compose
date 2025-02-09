package com.iptriana.rickymortywiki.ui.home.tabs.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toolingGraphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import com.iptriana.rickymortywiki.domain.model.CharacterModel
import com.iptriana.rickymortywiki.ui.core.ex.vertical
import com.iptriana.rickymortywiki.ui.theme.BackgroundPrimaryColor
import com.iptriana.rickymortywiki.ui.theme.DefaultTextColor
import com.iptriana.rickymortywiki.ui.theme.Green
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import rickymortywiki.composeapp.generated.resources.Res
import rickymortywiki.composeapp.generated.resources.rickface

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharactersScreen(navigateToCharacterDetail: (CharacterModel) -> Unit) {
    val charactersViewModel = koinViewModel<CharactersViewModel>()
    val characterState by charactersViewModel.state.collectAsState()
    val characters = characterState.characters.collectAsLazyPagingItems()
    CharacterGirdList(characters = characters, characterState = characterState, navigateToCharacterDetail = navigateToCharacterDetail)
}

@Composable
fun CharacterGirdList(
    characterState: CharacterState,
    characters: LazyPagingItems<CharacterModel>,
    navigateToCharacterDetail: (CharacterModel) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize().background(BackgroundPrimaryColor).padding(horizontal = 16.dp).toolingGraphicsLayer(),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            characterState.characterOfTheDay?.let {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Characters",
                    fontSize = 24.sp,
                    color = DefaultTextColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(6.dp))
                CharacterOfTheDay(characterModel = it)
            }
        }
        when {
            characters.loadState.refresh is LoadState.Loading && characters.itemCount == 0 -> {
                // Initial loading
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(Modifier.size(64.dp), color = Green)
                    }
                }
            }

            characters.loadState.refresh is LoadState.NotLoading && characters.itemCount == 0 -> {
                // No data
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "No hay datos :(", textAlign = TextAlign.Center)
                    }
                }
            }

            else -> {
                items(characters.itemCount) { index ->
                    characters[index]?.let { character ->
                        CharacterItemList(character = character){ characterModel ->
                            navigateToCharacterDetail(characterModel)
                        }
                    }
                }
                if (characters.loadState.append is LoadState.Loading) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Box(
                            modifier = Modifier.fillMaxHeight().height(100.dp).fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(Modifier.size(64.dp), color = Color.Red)
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun CharacterItemList(character: CharacterModel, onItemSelected: (CharacterModel) -> Unit = {}) {
    Box(
        modifier = Modifier.clip(RoundedCornerShape(24))
            .border(2.dp, Green, shape = RoundedCornerShape(0, 24, 0, 24))
            .fillMaxWidth()
            .height(150.dp)
            .clickable { onItemSelected(character) },
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            model = character.image,
            placeholder = painterResource(Res.drawable.rickface),
        )
        Box(
            modifier = Modifier.fillMaxSize().height(60.dp).background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0f),
                        Color.Black.copy(alpha = 0.1f),
                        Color.Black.copy(alpha = 0.2f),
                        Color.Black.copy(alpha = 0.3f),
                        Color.Black.copy(alpha = 0.4f),
                        Color.Black.copy(alpha = 0.5f),
                        Color.Black.copy(alpha = 0.6f),


                        )
                )
            )
        ) {
            Text(
                modifier = Modifier.align(Alignment.BottomStart).padding(16.dp),
                text = character.name,
                fontSize = 18.sp,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun CharacterOfTheDay(characterModel: CharacterModel? = null) {
    Card(modifier = Modifier.fillMaxWidth().height(400.dp).padding(vertical = 16.dp), shape = RoundedCornerShape(12)) {
        if (characterModel == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(Modifier.background(Green))
            }
        } else {
            Box(contentAlignment = Alignment.BottomStart) {
                Box(modifier = Modifier.fillMaxSize().background(Green.copy(alpha = 0.5f)))
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
                        .padding(horizontal = 24.dp, vertical = 24.dp)
                        .fillMaxHeight()
                        .vertical()
                        .rotate(-90f)
                )
            }
        }
    }
}