package com.iptriana.rickymortywiki.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.iptriana.rickymortywiki.domain.model.CharacterModel
import com.iptriana.rickymortywiki.domain.model.EpisodeModel
import com.iptriana.rickymortywiki.ui.core.components.TextTitle
import com.iptriana.rickymortywiki.ui.core.ex.aliveBorder
import com.iptriana.rickymortywiki.ui.theme.BackgroundPrimaryColor
import com.iptriana.rickymortywiki.ui.theme.BackgroundSecondaryColor
import com.iptriana.rickymortywiki.ui.theme.BackgroundTertiaryColor
import com.iptriana.rickymortywiki.ui.theme.DefaultTextColor
import com.iptriana.rickymortywiki.ui.theme.Green
import com.iptriana.rickymortywiki.ui.theme.Pink
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf
import rickymortywiki.composeapp.generated.resources.Res
import rickymortywiki.composeapp.generated.resources.space

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharacterDetailsScreen(characterModel: CharacterModel) {

    val characterDetailViewModel =
        koinViewModel<CharacterDetailViewModel>(parameters = { parametersOf(characterModel) })
    val state by characterDetailViewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.fillMaxSize().background(BackgroundPrimaryColor)
            .verticalScroll(scrollState)
    ) {
        MainHeader(state.characterModel)
//        Spacer(Modifier.height(16.dp))
        Column(
            modifier = Modifier.fillMaxSize()
                .clip(RoundedCornerShape(topStartPercent = 10, topEndPercent = 10)).background(
                    BackgroundSecondaryColor
                )
        ) {
            CharacterInformation(state.characterModel)
            CharacterEpisodeList(state.episodes)
        }

    }
}

@Composable
fun CharacterEpisodeList(episodes: List<EpisodeModel>?) {
    ElevatedCard(
        modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors().copy(containerColor = BackgroundTertiaryColor)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(16.dp)) {
            if (episodes == null) {
                CircularProgressIndicator(color = Color.Green)
            } else {
                Column {
                    TextTitle("About the character")
                    Spacer(Modifier.height(16.dp))

                    episodes.forEach {
                        EpisodeItem(it)
                        Spacer(Modifier.height(4.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun EpisodeItem(episode: EpisodeModel) {
    Column {
        Text(episode.name, color = Green, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text(episode.episode, color = DefaultTextColor, fontSize = 16.sp)
    }
} 

@Composable
fun CharacterInformation(characterModel: CharacterModel) {
    ElevatedCard(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors().copy(containerColor = BackgroundTertiaryColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TextTitle("About the character")
            Spacer(Modifier.height(16.dp))
            InformationDetail("Gender: ", characterModel.gender)
            InformationDetail("Origin: ", characterModel.origin)
        }
    }
}

@Composable
fun InformationDetail(title: String, detail: String) {
    Row {
        Text(title, color = DefaultTextColor, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.width(4.dp))
        Text(detail, color = Green, fontSize = 16.sp)
    }
}

@Composable
fun MainHeader(characterModel: CharacterModel) {
    Box(modifier = Modifier.fillMaxWidth().height(300.dp)) {
        Image(
            painter = painterResource(Res.drawable.space),
            contentDescription = "background header",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        CharacterHeader(characterModel = characterModel)
    }
}

@Composable
fun CharacterHeader(characterModel: CharacterModel) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(topStartPercent = 10, topEndPercent = 10))
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                characterModel.name,
                color = Pink,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text("Especie: ${characterModel.species}", color = Color.Black, fontSize = 16.sp)
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(16.dp))
            Box(contentAlignment = Alignment.TopCenter) {
                Box(
                    modifier = Modifier.size(205.dp).clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = characterModel.image,
                        contentDescription = "Character Image",
                        modifier = Modifier.size(190.dp).clip(CircleShape)
                            .aliveBorder(characterModel.isAlive),
                        contentScale = ContentScale.Crop
                    )
                }

                val aliveCopy = if (characterModel.isAlive) "ALIVE" else "DEAD"
                val aliveColor = if (characterModel.isAlive) Green else Red
                Text(
                    aliveCopy,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clip(RoundedCornerShape(30)).background(aliveColor)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
            Spacer(Modifier.weight(1f))
        }

    }
}