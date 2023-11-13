package com.iptrianaa.therickandmortywiki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.iptrianaa.therickandmortywiki.ui.theme.TheRickAndMortyWikiTheme
import com.iptrianaa.therickandmortywiki.ui.viewmodel.MainViewModel

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
    LazyVerticalGrid(
        contentPadding = PaddingValues(4.dp),
        columns = GridCells.Fixed(2)
    ) {
//        items(items = paging.itemSnapshotList, key = { it!!.url }) { items ->
//            val color = if (items?.status == "Alive") Color.Green else Color.Red
//            Card(modifier = Modifier.padding(4.dp)) {
//                Column {
//                    AsyncImage(
//                        model = items?.image,
//                        modifier = Modifier.fillMaxWidth(),
//                        contentScale = ContentScale.Crop,
//                        contentDescription = "Character's image"
//                    )
//                    Text(
//                        text = items!!.name,
//                        fontSize = 18.sp,
//                        fontWeight = Bold,
//                        maxLines = 2,
//                        modifier = Modifier
//                            .padding(8.dp, 2.dp)
//                            .height(48.dp)
//                    )
//
//                    Row(
//                        modifier = Modifier
//                            .align(Alignment.End)
//                            .padding(4.dp, 0.dp)
//                    ) {
//                        Box(
//                            modifier = Modifier
//                                .size(8.dp)
//                                .clip(CircleShape)
//                                .background(color)
//                                .align(CenterVertically)
//                        )
//                        Text(
//                            text = "${items.status} - ${items.species}",
//                            fontSize = 12.sp,
//                            modifier = Modifier.padding(4.dp, 0.dp)
//                        )
//                    }
//                }
//            }
//            when (val state = paging.loadState.refresh) { //FIRST LOAD
//                is LoadState.Error -> {
//                    //TODO Error Item
//                    //state.error to get error message
//                }
//
//                is LoadState.Loading -> { // Loading UI
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center,
//                    ) {
//                        Text(
//                            modifier = Modifier
//                                .padding(8.dp),
//                            text = "Refresh Loading"
//                        )
//
//                        CircularProgressIndicator(color = Color.Black)
//                    }
//                }
//
//                else -> {}
//            }
//
//
//            when (val state = paging.loadState.append) { // Pagination
//                is LoadState.Error -> {
//                    //TODO Pagination Error Item
//                    //state.error to get error message
//                }
//
//                is LoadState.Loading -> { // Pagination Loading UI
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center,
//                    ) {
//                        Text(text = "Pagination Loading")
//
//                        CircularProgressIndicator(color = Color.Black)
//                    }
//                }
//
//                else -> {}
//            }
//        }
    }
}