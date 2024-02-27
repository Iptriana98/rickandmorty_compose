package com.iptrianaa.therickandmortywiki.data.remote.datasources.character

import com.google.gson.annotations.SerializedName
import com.iptrianaa.therickandmortywiki.data.remote.datasources.Info
import com.iptrianaa.therickandmortywiki.data.remote.datasources.Location

data class Character (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("type") val type: String,
    @SerializedName("origin") val origin: Location,
    @SerializedName("location") val location: Location,
    @SerializedName("image") val image: String,
    @SerializedName("episode") val episode: List<String>,
    @SerializedName("url") val url: String,
    @SerializedName("created") val created: String,
)

data class CharacterResult(
    @SerializedName("info") val info: Info,
    @SerializedName("results") val results: List<Character>,
)