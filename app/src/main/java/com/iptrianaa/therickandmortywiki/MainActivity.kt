package com.iptrianaa.therickandmortywiki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.iptrianaa.therickandmortywiki.ui.theme.TheRickAndMortyWikiTheme
import com.iptrianaa.therickandmortywiki.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheRickAndMortyWikiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
//                    viewModel.getCharacters()
                    Greeting(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(viewModel: MainViewModel) {
    val pagin = viewModel.pager.collectAsLazyPagingItems()

//    if (characters.isNullOrEmpty()) Box(
//        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
//    ) {
//        CircularProgressIndicator(
//            modifier = Modifier
//                .height(40.dp)
//                .width(40.dp)
//        )
//    }
//    else
    LazyVerticalGrid(
        contentPadding = PaddingValues(4.dp),
        columns = GridCells.Fixed(2)
    ) {
        items(pagin.itemSnapshotList) { items ->
            val color = if (items?.status == "Alive") Color.Green else Color.Red
            Card(modifier = Modifier.padding(4.dp)) {
                Column {
                    AsyncImage(
                        model = items?.image,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Character's image"
                    )
                    Text(
                        text = items!!.name,
                        fontSize = 18.sp,
                        fontWeight = Bold,
                        maxLines = 2,
                        modifier = Modifier
                            .padding(8.dp, 2.dp)
                            .height(48.dp)
                    )

                    Row(modifier = Modifier
                        .align(Alignment.End)
                        .padding(4.dp, 0.dp)) {
                        Box(modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(color)
                            .align(CenterVertically)
                        )
                        Text(
                            text = "${items.status} - ${items.species}",
                            fontSize = 12.sp,
                            modifier = Modifier.padding(4.dp, 0.dp)
                        )
                    }
                }
            }
        }
    }
}