package com.iptrianaa.therickandmortywiki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    viewModel.getCharacters()
                    Greeting(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(viewModel: MainViewModel) {
    val characters by viewModel.characters.observeAsState(initial = listOf())
    if (characters.isNullOrEmpty())
        CircularProgressIndicator(
            modifier = Modifier
                .height(40.dp)
                .width(40.dp)
        )
    else Column {
        characters!!.map {
            Text(
                text = it.name
            )
        }
    }
    
}