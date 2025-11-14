package com.example.lingai.ui.screens.lesson

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.lingai.ui.components.ContentColumn
import com.example.lingai.ui.components.LazyVerticalTopics
import com.example.lingai.ui.screens.dialogs.TopicDetailsDialog
import com.example.lingai.ui.theme.GreenPrimary
import com.example.lingai.ui.theme.TextPrimary


@Composable
fun LessonsPage(navController: NavController, viewModel: LessonsViewModel = hiltViewModel()) {
    val selectedTopic by viewModel.selectedTopic.collectAsState()
    val topicsUi by viewModel.topicsUi.collectAsState()

    // Слушаем навигацию из VM
    LaunchedEffect(Unit) {
        viewModel.navigateToLearn.collect { topicId ->
            navController.navigate("learn/${topicId}")
            Log.d("Lesson", "LessonsPage: $topicId")
        }
    }

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
        LazyVerticalTopics(
            topics = topicsUi,
            onTopicSelected = { viewModel.onTopicClicked(it) }
        )
    }
    // 👇 В этом блоке открывается всплывающее окно
    AnimatedVisibility(
        visible = selectedTopic != null,
        enter = slideInVertically { it } + fadeIn(),
        exit = slideOutVertically { it } + fadeOut()
    ) {
        selectedTopic?.let { topic ->
            TopicDetailsDialog(
                topic = topic,
                onDismiss = { viewModel.closeDialog() },
                onStartLearning = { viewModel.startLearning() }
            )
        }
    }
}

//@Preview
//@Composable
//fun LessonsPagePrew() {
//   LessonsPage()
//}
