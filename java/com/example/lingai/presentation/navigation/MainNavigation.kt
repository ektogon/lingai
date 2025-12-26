package com.example.lingai.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainNavigation(darkTheme: Boolean ,
                   onThemeUpdate: () -> Unit) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val hideBottomBarRoutes = listOf(
        "learn"
    )
    val navItems = listOf(
        NavigationItem.Home,
        NavigationItem.Lessons,
        NavigationItem.Generated,
        NavigationItem.Exam,
        NavigationItem.Profile
    )
    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        bottomBar = {
            val shouldShowBottomBar = hideBottomBarRoutes.none { prefix ->
                currentRoute?.startsWith(prefix) == true
            }
            AnimatedVisibility(
                visible = shouldShowBottomBar,
            ) {
                BottomNavigationBar(
                    tabs = navItems,
                    currentRoute = currentRoute ?: NavigationItem.Home.route,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavGraph(navController = navController, darkTheme, onThemeUpdate)
        }
    }
}
