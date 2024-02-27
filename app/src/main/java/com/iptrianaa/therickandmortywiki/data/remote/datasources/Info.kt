package com.iptrianaa.therickandmortywiki.data.remote.datasources

import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("count") val count: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("prev") val prev: String?,
){
    fun getNext() = next?.split("=")?.get(1)?.toInt()
}
