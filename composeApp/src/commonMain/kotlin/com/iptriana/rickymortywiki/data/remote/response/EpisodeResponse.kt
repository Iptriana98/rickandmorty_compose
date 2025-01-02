package com.iptriana.rickymortywiki.data.remote.response

import com.iptriana.rickymortywiki.domain.model.EpisodeModel
import com.iptriana.rickymortywiki.domain.model.SeasonEpisode
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeResponse(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
) {
    fun toDomain(): EpisodeModel = EpisodeModel(
        id = id,
        name = name,
        airDate = airDate,
        episode = episode,
        characters = characters.map { url -> url.substringAfter("/") },
        url = url,
        created = created,
        season =
    )

    private fun getUrlFromSeason(season: SeasonEpisode): String = when (season) {
        SeasonEpisode.SEASON_1 -> "https://www.youtube.com/watch?v=8BEzj2kRjO8&ab_channel=RottenTomatoesTV"
        SeasonEpisode.SEASON_2 -> "https://www.youtube.com/watch?v=SXwf_9xJu5c&ab_channel=Yusuto"
        SeasonEpisode.SEASON_3 -> "https://www.youtube.com/watch?v=Bmg2vXOQ3kM&ab_channel=SeriesTrailerMP"
        SeasonEpisode.SEASON_4 -> "https://www.youtube.com/watch?v=bLI2-v264No&ab_channel=RottenTomatoesTV"
        SeasonEpisode.SEASON_5 -> "https://www.youtube.com/watch?v=yC1UxW8vcDo&ab_channel=RottenTomatoesTV"
        SeasonEpisode.SEASON_6 -> "https://www.youtube.com/watch?v=jerFRSQW9g8&ab_channel=RottenTomatoesTV"
        SeasonEpisode.SEASON_7 -> "https://www.youtube.com/watch?v=PkZtVBNkmso&ab_channel=RottenTomatoesTV"
        else -> "https://www.youtube.com/watch?v=PkZtVBNkmso&ab_channel=RottenTomatoesTV"
    }

    private fun getSeasonFromEpisodeCode(episode: String): SeasonEpisode = when {
        episode.contains("S01") -> SeasonEpisode.SEASON_1
        episode.contains("S02") -> SeasonEpisode.SEASON_2
        episode.contains("S03") -> SeasonEpisode.SEASON_3
        episode.contains("S04") -> SeasonEpisode.SEASON_4
        episode.contains("S05") -> SeasonEpisode.SEASON_5
        episode.contains("S06") -> SeasonEpisode.SEASON_6
        episode.contains("S07") -> SeasonEpisode.SEASON_7
        else -> SeasonEpisode.UNKNOWN
    }
}
