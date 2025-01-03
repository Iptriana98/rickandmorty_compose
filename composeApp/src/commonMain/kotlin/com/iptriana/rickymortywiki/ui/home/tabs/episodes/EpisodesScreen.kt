package com.iptriana.rickymortywiki.ui.home.tabs.episodes

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.collectAsLazyPagingItems
import com.iptriana.rickymortywiki.domain.model.EpisodeModel
import com.iptriana.rickymortywiki.domain.model.SeasonEpisode
import com.iptriana.rickymortywiki.ui.core.components.PagingLoadingState
import com.iptriana.rickymortywiki.ui.core.components.PagingType
import com.iptriana.rickymortywiki.ui.core.components.PagingWrapper
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import rickymortywiki.composeapp.generated.resources.Res
import rickymortywiki.composeapp.generated.resources.season1
import rickymortywiki.composeapp.generated.resources.season2
import rickymortywiki.composeapp.generated.resources.season3
import rickymortywiki.composeapp.generated.resources.season4
import rickymortywiki.composeapp.generated.resources.season5
import rickymortywiki.composeapp.generated.resources.season6
import rickymortywiki.composeapp.generated.resources.season7

@OptIn(KoinExperimentalAPI::class)
@Composable
fun EpisodesScreen() {
    val episodesViewModel = koinViewModel<EpisodesViewModel>()

    val episodesState by episodesViewModel.state.collectAsState()
    val episodes = episodesState.episodes.collectAsLazyPagingItems()
    Box(Modifier.fillMaxSize()) {
        PagingWrapper(
            pagingType = PagingType.ROW,
            pagingItems = episodes,
            itemView = { EpisodeItemList(episode = it) },
            emptyView = {},
            initialView = { PagingLoadingState() }
        )
    }
}

@Composable
fun EpisodeItemList(episode: EpisodeModel) {
    Column(modifier = Modifier.width(120.dp).padding(horizontal = 8.dp).clickable { }) {
        Image(
            modifier = Modifier.height(200.dp).fillMaxWidth(),
            contentDescription = null,
            contentScale = ContentScale.Inside,
            painter = painterResource(getSeasonImage(episode.season))
        )
    }
}

private fun getSeasonImage(season: SeasonEpisode): DrawableResource = when (season) {
    SeasonEpisode.SEASON_1 -> Res.drawable.season1
    SeasonEpisode.SEASON_2 -> Res.drawable.season2
    SeasonEpisode.SEASON_3 -> Res.drawable.season3
    SeasonEpisode.SEASON_4 -> Res.drawable.season4
    SeasonEpisode.SEASON_5 -> Res.drawable.season5
    SeasonEpisode.SEASON_6 -> Res.drawable.season6
    SeasonEpisode.SEASON_7 -> Res.drawable.season7
    SeasonEpisode.UNKNOWN -> Res.drawable.season1
}