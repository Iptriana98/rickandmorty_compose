package com.iptriana.rickymortywiki.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.iptriana.rickymortywiki.ui.core.navigation.bottom_navigation.BottomBarItem
import com.iptriana.rickymortywiki.ui.core.navigation.bottom_navigation.NavigationBottomWrapper
import com.iptriana.rickymortywiki.ui.theme.BackgroundPrimaryColor
import com.iptriana.rickymortywiki.ui.theme.BackgroundSecondaryColor
import com.iptriana.rickymortywiki.ui.theme.BackgroundTertiaryColor
import com.iptriana.rickymortywiki.ui.theme.DefaultTextColor
import com.iptriana.rickymortywiki.ui.theme.Green
import org.jetbrains.compose.resources.painterResource
import rickymortywiki.composeapp.generated.resources.Res
import rickymortywiki.composeapp.generated.resources.ricktoolbar

@Composable
fun HomeScreen(mainNavController: NavHostController) {
    val items = listOf(BottomBarItem.Episodes(), BottomBarItem.Characters())
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigation(items, navController) },
        topBar = { TopBar() }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            NavigationBottomWrapper(navController, mainNavController)
        }
    }
}

@Composable
fun TopBar() {
    Box(modifier = Modifier.fillMaxWidth().background(BackgroundPrimaryColor), contentAlignment = Alignment.TopCenter) {
        Image(
            painter = painterResource(Res.drawable.ricktoolbar),
            contentDescription = null,
            modifier = Modifier.padding(start = 16.dp, top = 32.dp, bottom = 8.dp),
        )
    }

}

@Composable
fun BottomNavigation(
    items: List<BottomBarItem>,
    navController: NavHostController
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination
    NavigationBar(containerColor = BackgroundSecondaryColor, contentColor = Green) {
        items.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Green,
                    selectedIconColor = BackgroundTertiaryColor,
                    selectedTextColor = Green,
                    unselectedIconColor = Green,
                    unselectedTextColor = DefaultTextColor
                ),
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                label = { Text(text = item.title, color = DefaultTextColor) },
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
