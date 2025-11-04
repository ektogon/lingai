package com.example.lingai.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lingai.ui.screens.ExamPage
import com.example.lingai.ui.screens.GeneratedTopicsPage
import com.example.lingai.ui.screens.HomePage
import com.example.lingai.ui.screens.LessonsPage
import com.example.lingai.ui.screens.ProfilePage
import kotlin.collections.listOf

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val navItems = listOf(
        NavigationItem.Home,
        NavigationItem.Lessons,
        NavigationItem.Generated,
        NavigationItem.Exam,
        NavigationItem.Profile
    )
    Scaffold(
        bottomBar = {
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
    ) {
        NavGraph(navHostController = navController)
    }
}
