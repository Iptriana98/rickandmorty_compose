package com.iptriana.rickymortywiki.ui.core.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.iptriana.rickymortywiki.ui.theme.DefaultTextColor

@Composable
fun TextTitle(text: String, modifier: Modifier = Modifier) {
    Text(text = text.uppercase(), color = DefaultTextColor, fontWeight = FontWeight.Bold, modifier = modifier)
}