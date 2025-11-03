package com.example.lingai.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lingai.ui.components.BottomNavigationBar
import com.example.lingai.ui.screens.ExamPage
import com.example.lingai.ui.screens.GeneratedTopicsPage
import com.example.lingai.ui.screens.HomePage
import com.example.lingai.ui.screens.LessonsPage
import com.example.lingai.ui.screens.ProfilePage

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Lessons : Screen("lessons")
    object Generated : Screen("generated")
    object Exam :Screen("exam")
    object Profile : Screen("profile")
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = currentRoute ?: Screen.Home.route,
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
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                HomePage()
            }
            composable(Screen.Lessons.route) {
                LessonsPage()
            }
            composable(Screen.Generated.route) {
                GeneratedTopicsPage()
            }
            composable(Screen.Exam.route) {
                ExamPage()
            }
            composable(Screen.Profile.route) {
                ProfilePage()
            }
        }
    }
}
