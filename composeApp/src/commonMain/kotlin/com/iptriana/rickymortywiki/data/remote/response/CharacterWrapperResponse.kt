package com.iptriana.rickymortywiki.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class CharacterWrapperResponse(
    val info: InfoResponse,
    val results: List<CharacterResponse>
)

@Serializable
data class InfoResponse(
    val pages: Int,
    val next: String?,
    val prev: String?
)