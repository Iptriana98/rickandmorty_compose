package com.iptriana.rickymortywiki

import androidx.compose.ui.window.ComposeUIViewController
import com.iptriana.rickymortywiki.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = { initKoin() }) { App() }