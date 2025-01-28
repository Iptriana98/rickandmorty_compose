package com.iptriana.rickymortywiki.ui.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.iptriana.rickymortywiki.domain.model.CharacterModel
import com.iptriana.rickymortywiki.ui.detail.CharacterDetailsScreen
import com.iptriana.rickymortywiki.ui.home.HomeScreen
import kotlinx.serialization.json.Json

@Composable
fun NavigationWrapper() {
    val mainNavController = rememberNavController()

    NavHost(navController = mainNavController, startDestination = "home") {
        composable(Routes.Home.route) {
            HomeScreen(mainNavController)
        }

        composable<CharacterDetails> { navBackStackEntry ->
            val characterEncoding = navBackStackEntry.toRoute<CharacterDetails>()
            val characterModel: CharacterModel =
                Json.decodeFromString<CharacterModel>(characterEncoding.character)
            CharacterDetailsScreen(characterModel = characterModel)
        }
    }
}