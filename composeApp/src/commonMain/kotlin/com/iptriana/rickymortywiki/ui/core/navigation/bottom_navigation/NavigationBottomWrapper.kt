package com.iptriana.rickymortywiki.ui.core.navigation.bottom_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.iptriana.rickymortywiki.ui.core.navigation.Routes
import com.iptriana.rickymortywiki.ui.home.tabs.characters.CharactersScreen
import com.iptriana.rickymortywiki.ui.home.tabs.episodes.EpisodesScreen

@Composable
fun NavigationBottomWrapper(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Episodes.route){
        composable(Routes.Episodes.route) {
            EpisodesScreen()
        }
        composable(Routes.Characters.route) {
            CharactersScreen()
        }
    }
}