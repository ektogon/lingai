package com.example.lingai.ui.fragments.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lingai.ui.components.CategoryCard
import com.example.lingai.data.LessonTopic
import com.example.lingai.data.WordItem
import com.example.lingai.data.WordStatus
import com.example.lingai.ui.theme.*

@Composable
fun PopularCategories() {
    val topics = listOf(
        LessonTopic(
            id = "family",
            title = "Семья и друзья",
            totalWords = 50,
            completedWords = 40,
            emoji = "👨‍👩‍👧",
            level = "B1",
            learningWords = 5,
            words = listOf(
                WordItem("Mother", "Мать", WordStatus.LEARNED),
                WordItem("Father", "Отец", WordStatus.LEARNED),
                WordItem("Friend", "Друг", WordStatus.IN_PROGRESS),
                WordItem("Child", "Ребёнок", WordStatus.NEW)
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
                WordItem("Music", "Музыка", WordStatus.IN_PROGRESS),
                WordItem("Painting", "Живопись", WordStatus.NEW),
                WordItem("Reading", "Чтение", WordStatus.LEARNED)
            )
        ),
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Популярные темы",
            style = MaterialTheme.typography.titleLarge,
            color = TextPrimary
        )
        Icon(
            Icons.Default.Star,
            contentDescription = null,
            tint = Gold,
            modifier = Modifier.size(18.dp)
        )
    }


    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        topics.forEach { topic ->
            CategoryCard(
                modifier = Modifier.weight(1f),
                topic = topic,
                onClick = {}
            )
            if (topic != topics.last()) {Spacer(modifier = Modifier.width(12.dp))}
        }
    }
}

@Preview
@Composable
fun CategoryCardPrew() {
    PopularCategories()
}
