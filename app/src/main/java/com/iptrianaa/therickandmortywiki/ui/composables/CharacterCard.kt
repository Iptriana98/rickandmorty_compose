package com.iptrianaa.therickandmortywiki.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.iptrianaa.therickandmortywiki.data.Character

/**
 * Created by iptrianaa on 2022-08-01
 * Composable used to display a character
 * @param character: Is the data class that contains the information of the character
 */
@Composable
fun CharacterCard(character: Character) {
    val color = if (character.status == "Alive") Color.Green else Color.Red
    Card {
        Column {
            AsyncImage(
                model = character.image,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
                contentDescription = "Character's image"
            )
            Text(
                text = character.name,
                fontSize = 18.sp,
                fontWeight = Bold,
                maxLines = 2,
                modifier = Modifier
                    .padding(8.dp, 2.dp)
                    .height(48.dp)
            )

            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(4.dp, 0.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(color)
                        .align(CenterVertically)
                )
                Text(
                    text = "${character.status} - ${character.species}",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(4.dp, 0.dp)
                )
            }
        }
    }
}