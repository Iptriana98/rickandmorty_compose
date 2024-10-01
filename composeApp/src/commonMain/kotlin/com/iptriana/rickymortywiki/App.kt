package com.iptriana.rickymortywiki

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.iptriana.rickymortywiki.ui.core.navigation.NavigationWrapper
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        NavigationWrapper()
    }
}