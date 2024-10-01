package com.iptriana.rickymortywiki.ui.core.navigation

sealed class Routes (val route: String) {
    data object Home: Routes("home")

    //Bottom Navigation
    data object Episodes: Routes("episodes")
    data object Locations: Routes("locations")
    data object Characters: Routes("characters")

}