package com.example.lingai.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lingai.data.model.LessonTopic
import com.example.lingai.data.model.Word
import com.example.lingai.data.model.WordStatus

@Composable
fun LazyVerticalTopics(topics: List<LessonTopic>,  onTopicSelected: (LessonTopic) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 15.dp)

    ) {
        var index: Int = 1
        items(topics) { topic ->

            index++
            CategoryCard(topic = topic, onClick = { onTopicSelected(topic) },  index=index)
        }
    }
}

@Preview
@Composable
fun LazyVerticalTopicsPrew() {
    val topics = listOf(
        LessonTopic(
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
    )
    var selectedTopic1 by remember { mutableStateOf<LessonTopic?>(null) }
    LazyVerticalTopics(topics, onTopicSelected = { selectedTopic1 = it })
}