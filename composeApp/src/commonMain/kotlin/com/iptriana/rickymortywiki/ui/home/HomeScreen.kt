package com.iptriana.rickymortywiki.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.iptriana.rickymortywiki.ui.core.navigation.bottom_navigation.BottomBarItem
import com.iptriana.rickymortywiki.ui.core.navigation.bottom_navigation.NavigationBottomWrapper

@Composable
fun HomeScreen(mainNavController: NavHostController) {
    val items = listOf(BottomBarItem.Episodes(), BottomBarItem.Characters())
    val navController = rememberNavController()

    Scaffold(bottomBar = { BottomNavigation(items, navController) }) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            NavigationBottomWrapper(navController, mainNavController)
        }
    }
}

@Composable
fun BottomNavigation(
    items: List<BottomBarItem>,
    navController: NavHostController
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination
    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                label = { Text(text = item.title) },
                alwaysShowLabel = false,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { item.icon() },
            )
        }
    }
}
