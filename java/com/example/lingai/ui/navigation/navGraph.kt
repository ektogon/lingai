package com.example.lingai.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lingai.ui.screens.ExamPage
import com.example.lingai.ui.screens.GeneratedTopicsPage
import com.example.lingai.ui.screens.HomePage
import com.example.lingai.ui.screens.LessonsPage
import com.example.lingai.ui.screens.ProfilePage

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = NavigationItem.Home.route,
    ) {
        composable(NavigationItem.Home.route) {
            HomePage()
        }
        composable(NavigationItem.Lessons.route) {
            LessonsPage()
        }
        composable(NavigationItem.Generated.route) {
            GeneratedTopicsPage()
        }
        composable(NavigationItem.Exam.route) {
            ExamPage()
        }
        composable(NavigationItem.Profile.route) {
            ProfilePage()
        }
    }
}