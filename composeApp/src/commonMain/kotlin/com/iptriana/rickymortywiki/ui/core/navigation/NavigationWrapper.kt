package com.iptriana.rickymortywiki.ui.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iptriana.rickymortywiki.ui.home.HomeScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home"){
        composable(Routes.Home.route) {
            HomeScreen(modifier = Modifier)
        }
    }
}