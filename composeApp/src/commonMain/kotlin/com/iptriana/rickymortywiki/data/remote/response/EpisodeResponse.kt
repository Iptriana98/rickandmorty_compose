package com.iptriana.rickymortywiki.data.remote.response

import com.iptriana.rickymortywiki.domain.model.EpisodeModel
import com.iptriana.rickymortywiki.domain.model.SeasonEpisode
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeResponse(
    val id: Int,
    val name: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
) {
    fun toDomain(): EpisodeModel {
        val season = getSeasonFromEpisodeCode(episode)
        return EpisodeModel(
            id = id,
            name = name,
            episode = episode,
            characters = characters.map { url -> url.substringAfterLast("/") },
            url = url,
            season = season,
            videoUrl = getUrlFromSeason(season)
        )
    }

    private fun getUrlFromSeason(season: SeasonEpisode): String = when (season) {
        SeasonEpisode.SEASON_1 -> "https://firebasestorage.googleapis.com/v0/b/rickymortykmp.firebasestorage.app/o/videoplayback.mp4?alt=media&token=3e4edd7e-19eb-4616-9569-f229584c713b"
        SeasonEpisode.SEASON_2 -> "https://firebasestorage.googleapis.com/v0/b/rickymortykmp.firebasestorage.app/o/videoplayback.mp4?alt=media&token=3e4edd7e-19eb-4616-9569-f229584c713b"
        SeasonEpisode.SEASON_3 -> "https://firebasestorage.googleapis.com/v0/b/rickymortykmp.firebasestorage.app/o/videoplayback.mp4?alt=media&token=3e4edd7e-19eb-4616-9569-f229584c713b"
        SeasonEpisode.SEASON_4 -> "https://firebasestorage.googleapis.com/v0/b/rickymortykmp.firebasestorage.app/o/videoplayback.mp4?alt=media&token=3e4edd7e-19eb-4616-9569-f229584c713b"
        SeasonEpisode.SEASON_5 -> "https://firebasestorage.googleapis.com/v0/b/rickymortykmp.firebasestorage.app/o/videoplayback.mp4?alt=media&token=3e4edd7e-19eb-4616-9569-f229584c713b"
        SeasonEpisode.SEASON_6 -> "https://firebasestorage.googleapis.com/v0/b/rickymortykmp.firebasestorage.app/o/videoplayback.mp4?alt=media&token=3e4edd7e-19eb-4616-9569-f229584c713b"
        SeasonEpisode.SEASON_7 -> "https://firebasestorage.googleapis.com/v0/b/rickymortykmp.firebasestorage.app/o/videoplayback.mp4?alt=media&token=3e4edd7e-19eb-4616-9569-f229584c713b"
        else -> "https://firebasestorage.googleapis.com/v0/b/rickymortykmp.firebasestorage.app/o/videoplayback.mp4?alt=media&token=3e4edd7e-19eb-4616-9569-f229584c713b"
    }

    private fun getSeasonFromEpisodeCode(episode: String): SeasonEpisode = when {
        episode.startsWith("S01") -> SeasonEpisode.SEASON_1
        episode.startsWith("S02") -> SeasonEpisode.SEASON_2
        episode.startsWith("S03") -> SeasonEpisode.SEASON_3
        episode.startsWith("S04") -> SeasonEpisode.SEASON_4
        episode.startsWith("S05") -> SeasonEpisode.SEASON_5
        episode.startsWith("S06") -> SeasonEpisode.SEASON_6
        episode.startsWith("S07") -> SeasonEpisode.SEASON_7
        else -> SeasonEpisode.UNKNOWN
    }
}
