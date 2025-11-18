package com.example.lingai.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.lingai.domain.model.LessonTopic
import com.example.lingai.domain.model.Word
import com.example.lingai.domain.model.WordStatus

@Composable
fun PopularCategories() {
    val topics = listOf(
        LessonTopic(
            id = 1,
            title = "Семья и друзья",
            translation = "Семья и друзья",
            totalWords = 50,
            completedWords = 40,
            emoji = "👨‍👩‍👧",
            level = "B1",
            learningWords = 5,
            words = listOf(
                Word("Mother", "Мать", "",WordStatus.LEARNED),
                Word("Father", "Отец", "",WordStatus.LEARNED),
                Word("Friend", "Друг", "",WordStatus.IN_PROGRESS),
                Word("Child", "Ребёнок", "",WordStatus.NEW)
            )
        ),
        LessonTopic(
            id = 2,
            title = "Семья и друзья",
            translation = "Хобби и увлечения",
            totalWords = 60,
            completedWords = 15,
            emoji = "🎨",
            level = "B2",
            learningWords = 10,
            words = listOf(
                Word("Music", "Музыка", "",WordStatus.IN_PROGRESS),
                Word("Painting", "Живопись","", WordStatus.NEW),
                Word("Reading", "Чтение", "",WordStatus.LEARNED)
            )
        ),
    )

//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        var index: Int = 1
//        topics.forEach { topic ->
//
//            index++
//            CategoryCard(
//                modifier = Modifier.weight(1f),
//                topic = topic,
//                onClick = {},
//                index = index
//            )
//            if (topic != topics.last()) {Spacer(modifier = Modifier.width(12.dp))}
//        }
//    }
}

@Preview
@Composable
fun CategoryCardPrew() {
    PopularCategories()
}
