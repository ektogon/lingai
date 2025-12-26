package com.example.lingai.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PopularCategories() {
//    val topics = listOf(
//        LessonTopicModel(
//            id = 1,
//            title = "Семья и друзья",
//            translation = "Семья и друзья",
//            totalWords = 50,
//            completedWords = 40,
//            emoji = "👨‍👩‍👧",
//            level = "B1",
//            learningWords = 5,
//            words = listOf(
//                WordModel(1,"Mother", "Мать", "","","",3),
//                WordModel(2,"Father", "Отец", ""),
//                WordModel(3,"Friend", "Друг", ""),
//                WordModel(4,"Child", "Ребёнок", "")
//            )
//        ),
//        LessonTopicModel(
//            id = 2,
//            title = "Семья и друзья",
//            translation = "Хобби и увлечения",
//            totalWords = 60,
//            completedWords = 15,
//            emoji = "🎨",
//            level = "B2",
//            learningWords = 10,
//            words = listOf(
//                WordModel("Music", "Музыка", "",WordStatus.IN_PROGRESS),
//                WordModel("Painting", "Живопись","", WordStatus.NEW),
//                WordModel("Reading", "Чтение", "",WordStatus.LEARNED)
//            )
//        ),
//    )

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
