package com.example.lingai.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lingai.model.LessonTopic
import com.example.lingai.model.Word
import com.example.lingai.model.WordStatus
import com.example.lingai.ui.components.ContentColumn
import com.example.lingai.ui.components.LazyVerticalTopics
import com.example.lingai.ui.dialogs.TopicDetailsDialog
import com.example.lingai.ui.theme.*
import com.google.gson.Gson


@Composable
fun LessonsPage(navController: NavController) {
    var selectedTopic by remember { mutableStateOf<LessonTopic?>(null) }
    val topics = listOf(
        LessonTopic(
            id = "a0",
            title = "Все слова A0",
            totalWords = 100,
            completedWords = 85,
            level = "A0",
            emoji = "📗",
            learningWords = 10,
            words = List(10) {
                Word(
                    original = "word${it + 1}",
                    translation = "слово${it + 1}",
                    status = when {
                        it < 7 -> WordStatus.LEARNED
                        it < 9 -> WordStatus.IN_PROGRESS
                        else -> WordStatus.NEW
                    }
                )
            }
        ),
        LessonTopic(
            id = "a1",
            title = "Все слова A1",
            totalWords = 150,
            completedWords = 120,
            level = "A1",
            emoji = "📘",
            learningWords = 20,
            words = List(15) {
                Word(
                    original = "word${it + 1}",
                    translation = "слово${it + 1}",
                    status = if (it < 10) WordStatus.LEARNED else WordStatus.IN_PROGRESS
                )
            }
        ),
        LessonTopic(
            id = "a2",
            title = "Все слова A2",
            totalWords = 200,
            completedWords = 45,
            level = "A2",
            emoji = "📙",
            learningWords = 25,
            words = List(20) {
                Word(
                    original = "word${it + 1}",
                    translation = "слово${it + 1}",
                    status = when {
                        it < 5 -> WordStatus.LEARNED
                        it < 10 -> WordStatus.IN_PROGRESS
                        else -> WordStatus.NEW
                    }
                )
            }
        ),
        LessonTopic(
            id = "b1",
            title = "Все слова B1",
            totalWords = 250,
            completedWords = 0,
            level = "B1",
            emoji = "📒",
            learningWords = 0,
            words = emptyList()
        ),
        LessonTopic(
            id = "b2",
            title = "Все слова B2",
            totalWords = 300,
            completedWords = 0,
            level = "B2",
            emoji = "📕",
            learningWords = 0,
            words = emptyList()
        ),
        LessonTopic(
            id = "c1",
            title = "Все слова C1",
            totalWords = 350,
            completedWords = 0,
            level = "C1",
            emoji = "📔",
            learningWords = 0,
            words = emptyList()
        ),
        LessonTopic(
            id = "c2",
            title = "Все слова C2",
            totalWords = 400,
            completedWords = 0,
            level = "C2",
            emoji = "📓",
            learningWords = 0,
            words = emptyList()
        ),
        LessonTopic(
            id = "greetings",
            title = "Базовые приветствия",
            totalWords = 30,
            completedWords = 30,
            emoji = "👋",
            level = "A1",
            learningWords = 0,
            words = listOf(
                Word("Hello", "Привет", WordStatus.LEARNED),
                Word("Good morning", "Доброе утро", WordStatus.LEARNED),
                Word("How are you?", "Как дела?", WordStatus.LEARNED),
                Word("Nice to meet you", "Рад встрече", WordStatus.LEARNED)
            )
        ),
        LessonTopic(
            id = "food",
            title = "Еда и напитки",
            totalWords = 80,
            completedWords = 60,
            emoji = "🍽️",
            level = "A1",
            learningWords = 10,
            words = listOf(
                Word("Bread", "Хлеб", WordStatus.LEARNED),
                Word("Water", "Вода", WordStatus.LEARNED),
                Word("Coffee", "Кофе", WordStatus.IN_PROGRESS),
                Word("Juice", "Сок", WordStatus.NEW)
            )
        ),
        LessonTopic(
            id = "travel",
            title = "Путешествия",
            totalWords = 70,
            completedWords = 35,
            emoji = "✈️",
            level = "A2",
            learningWords = 20,
            words = listOf(
                Word("Ticket", "Билет", WordStatus.LEARNED),
                Word("Airport", "Аэропорт", WordStatus.IN_PROGRESS),
                Word("Luggage", "Багаж", WordStatus.NEW)
            )
        ),
        LessonTopic(
            id = "work",
            title = "Работа и офис",
            totalWords = 90,
            completedWords = 0,
            emoji = "💼",
            level = "A2",
            learningWords = 0,
            words = emptyList()
        ),
        LessonTopic(
            id = "family",
            title = "Семья и друзья",
            totalWords = 50,
            completedWords = 40,
            emoji = "👨‍👩‍👧",
            level = "B1",
            learningWords = 5,
            words = listOf(
                Word("Mother", "Мать", WordStatus.LEARNED),
                Word("Father", "Отец", WordStatus.LEARNED),
                Word("Friend", "Друг", WordStatus.IN_PROGRESS),
                Word("Child", "Ребёнок", WordStatus.NEW)
            )
        ),
        LessonTopic(
            id = "hobbies",
            title = "Хобби и увлечения",
            totalWords = 60,
            completedWords = 15,
            emoji = "🎨",
            level = "B2",
            learningWords = 10,
            words = listOf(
                Word("Music", "Музыка", WordStatus.IN_PROGRESS),
                Word("Painting", "Живопись", WordStatus.NEW),
                Word("Reading", "Чтение", WordStatus.LEARNED)
            )
        ),
        LessonTopic(
            id = "weather",
            title = "Погода и природа",
            totalWords = 55,
            completedWords = 25,
            emoji = "🌤️",
            level = "C1",
            learningWords = 15,
            words = listOf(
                Word("Rain", "Дождь", WordStatus.LEARNED),
                Word("Cloud", "Облако", WordStatus.IN_PROGRESS),
                Word("Wind", "Ветер", WordStatus.NEW)
            )
        ),
        LessonTopic(
            id = "shopping",
            title = "Покупки",
            totalWords = 65,
            completedWords = 0,
            emoji = "🛍️",
            level = "C2",
            learningWords = 0,
            words = emptyList()
        ),
        LessonTopic(
            id = "health",
            title = "Здоровье",
            totalWords = 75,
            completedWords = 10,
            emoji = "💊",
            level = "C1",
            learningWords = 10,
            words = listOf(
                Word("Doctor", "Доктор", WordStatus.IN_PROGRESS),
                Word("Hospital", "Больница", WordStatus.NEW),
                Word("Medicine", "Лекарство", WordStatus.NEW)
            )
        )
    )



    ContentColumn{
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.MenuBook,
                contentDescription = null,
                tint = GreenPrimary,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Темы",
                style = MaterialTheme.typography.titleLarge,
                color = TextPrimary
            )
        }
        LazyVerticalTopics(topics, onTopicSelected = { selectedTopic = it })
    }
    // 👇 В этом блоке открывается всплывающее окно
    AnimatedVisibility(
        visible = selectedTopic != null,
        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { it}) + fadeOut()
    ) {
        selectedTopic?.let {topic ->
            TopicDetailsDialog(
                topic = topic,
                onDismiss = { selectedTopic = null },
                onStartLearning = {
                    val topicJson = Gson().toJson(selectedTopic)
                    selectedTopic = null
                    navController.navigate("learn/$topicJson")
                }
            )
        }
    }
}

@Preview
@Composable
fun LessonsPagePrew(){
//    LessonsPage()
}
