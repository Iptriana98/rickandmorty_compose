package com.iptriana.rickymortywiki

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform