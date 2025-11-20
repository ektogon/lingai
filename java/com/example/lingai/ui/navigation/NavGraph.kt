package com.example.lingai.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.lingai.ui.screens.exam.ExamPage
import com.example.lingai.ui.screens.generate.GeneratedTopicsPage
import com.example.lingai.ui.screens.home.HomePage
import com.example.lingai.ui.screens.learn.LearnWordScreen
import com.example.lingai.ui.screens.learn.LearnWordViewModel
import com.example.lingai.ui.screens.lesson.LessonsPage
import com.example.lingai.ui.screens.profile.ProfilePage

@Composable
fun NavGraph(
    navController: NavHostController,
    darkTheme: Boolean ,
    onThemeUpdate: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Home.route,
    ) {
        composable(NavigationItem.Home.route) {
            HomePage(navController = navController)
        }
        composable(NavigationItem.Lessons.route) {
            LessonsPage(navController = navController)
        }
        composable(NavigationItem.Generated.route) {
            GeneratedTopicsPage()
        }
        composable(NavigationItem.Exam.route) {
            ExamPage()
        }
        composable(NavigationItem.Profile.route) {
            ProfilePage(darkTheme,onThemeUpdate)
        }
        composable(
            route = "learn/{topicId}",
            arguments = listOf(navArgument("topicId") { type = NavType.IntType }
            )) { backStack ->
            val topicId = backStack.arguments?.getInt("topicId")!!
            val viewModel: LearnWordViewModel = hiltViewModel()

            LaunchedEffect(topicId) {
                viewModel.loadTopic(topicId)
            }

            val state by viewModel.state.collectAsState()

            LearnWordScreen(
                state = state,
                onEvent = viewModel::onEvent,
                onClose = { navController.popBackStack() }
            )
        }
    }
}
