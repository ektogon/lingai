package com.example.lingai.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lingai.data.model.LessonTopic
import com.example.lingai.data.model.Question
import com.example.lingai.ui.screens.exam.ExamPage
import com.example.lingai.ui.screens.generate.GeneratedTopicsPage
import com.example.lingai.ui.screens.home.HomePage
import com.example.lingai.ui.screens.lesson.LessonsPage
import com.example.lingai.ui.screens.profile.ProfilePage
import com.example.lingai.ui.screens.learn.LearnWordScreen
import com.google.gson.Gson

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
            LessonsPage(navController = navHostController)
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
        composable("learn/{topicJson}") { backStackEntry ->
            val topicJson = backStackEntry.arguments?.getString("topicJson")
            if (topicJson != null) {
                val topic = Gson().fromJson(topicJson, LessonTopic::class.java)

                val questions = topic.words.map { word ->
                    Question(
                        correctAnswer = word,
                        variants = listOf(
                            word,
                            word.copy(translation = "Ошибка 1"),
                            word.copy(translation = "Ошибка 2"),
                            word.copy(translation = "Ошибка 3")
                        ).shuffled(),
                        correctIndex = 0
                    )
                }

                LearnWordScreen(
                    questions = questions,
                    onClose = { navHostController.popBackStack() }
                )
            } else {
                Text("Ошибка: не удалось загрузить тему.")
            }
        }

    }
}