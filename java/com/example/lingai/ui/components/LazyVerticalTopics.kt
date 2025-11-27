package com.example.lingai.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lingai.domain.models.LessonTopicModel
import com.example.lingai.ui.model.LessonTopicUi

@Composable
fun LazyVerticalTopics(
    topics: List<LessonTopicUi>,
    onTopicSelected: (LessonTopicModel) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 15.dp)
    ) {
        items(topics) { item ->
            CategoryCard(
                data = item,
                onClick = { onTopicSelected(item.topic) }
            )
        }
    }
}

//@Preview
//@Composable
//fun LazyVerticalTopicsPrew() {
//    val topics = listOf(
//        LessonTopic(
//            id = 1,
//            title = "Все слова A0",
//            totalWords = 100,
//            completedWords = 85,
//            level = "A0",
//            emoji = "📗",
//            learningWords = 10,
//            words = List(10) {
//                    Word(
//                    original = "word${it + 1}",
//                    translation = "слово${it + 1}",
//                    status = when {
//                        it < 7 -> WordStatus.LEARNED
//                        it < 9 -> WordStatus.IN_PROGRESS
//                        else -> WordStatus.NEW
//                    }
//                )
//            }
//        ),
//        LessonTopic(
//            id = 2,
//            title = "Все слова A1",
//            totalWords = 150,
//            completedWords = 120,
//            level = "A1",
//            emoji = "📘",
//            learningWords = 20,
//            words = List(15) {
//                Word(
//                    original = "word${it + 1}",
//                    translation = "слово${it + 1}",
//                    status = if (it < 10) WordStatus.LEARNED else WordStatus.IN_PROGRESS
//                )
//            }
//        ),
//    )
//    var selectedTopic1 by remember { mutableStateOf<LessonTopic?>(null) }
//    LazyVerticalTopics(topics, onTopicSelected = { selectedTopic1 = it })
//}