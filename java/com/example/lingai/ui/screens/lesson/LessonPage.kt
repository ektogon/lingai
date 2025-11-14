package com.example.lingai.ui.screens.lesson

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lingai.data.model.LessonTopic
import com.example.lingai.data.model.Word
import com.example.lingai.data.model.WordStatus
import com.example.lingai.ui.components.ContentColumn
import com.example.lingai.ui.components.LazyVerticalTopics
import com.example.lingai.ui.screens.dialogs.TopicDetailsDialog
import com.example.lingai.ui.theme.*
import com.google.gson.Gson


@Composable
fun LessonsPage(navController: NavController, viewModel: LessonsViewModel = hiltViewModel()) {
    var selectedTopic by remember { mutableStateOf<LessonTopic?>(null) }
    val topics by viewModel.topics.collectAsState()

    ContentColumn {
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
        LazyVerticalTopics(topics = topics.map {
            LessonTopic(
                title = it.title,
                totalWords = it.totalWords,
                completedWords = it.completedWords,
                learningWords = it.learningWords,
                level = it.level,
                emoji = it.emoji,
                words = emptyList()
            )
        }, onTopicSelected = { selectedTopic = it })
    }
    // 👇 В этом блоке открывается всплывающее окно
    AnimatedVisibility(
        visible = selectedTopic != null,
        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
    ) {
        selectedTopic?.let { topic ->
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

//@Preview
//@Composable
//fun LessonsPagePrew() {
//   LessonsPage()
//}
