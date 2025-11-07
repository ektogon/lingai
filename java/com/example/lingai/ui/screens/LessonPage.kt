package com.example.lingai.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingai.ui.components.CardBlock
import com.example.lingai.ui.components.ContentColumn
import com.example.lingai.ui.components.ProgressBar
import com.example.lingai.ui.theme.*

data class LessonTopic(
    val id: String,
    val title: String,
    val totalWords: Int,
    val completedWords: Int,
    val level: String? = null,
    val emoji: String,
)

@Composable
fun LessonsPage() {
    val topics = listOf(
        LessonTopic("a0", "Все слова A0", 100, 85, "A0", "📗"),
        LessonTopic("a1", "Все слова A1", 150, 120, "A1", "📘"),
        LessonTopic("a2", "Все слова A2", 200, 45, "A2", "📙"),
        LessonTopic("b1", "Все слова B1", 250, 0, "B1", "📒"),
        LessonTopic("b2", "Все слова B2", 300, 0, "B2", "📕"),
        LessonTopic("c1", "Все слова C1", 350, 0, "C1", "📔"),
        LessonTopic("c2", "Все слова C2", 400, 0, "C2", "📓"),
        LessonTopic("greetings", "Базовые приветствия", 30, 30, emoji = "👋"),
        LessonTopic("food", "Еда и напитки", 80, 60, emoji = "🍽️"),
        LessonTopic("travel", "Путешествия", 70, 35, emoji = "✈️"),
        LessonTopic("work", "Работа и офис", 90, 0, emoji = "💼"),
        LessonTopic("family", "Семья и друзья", 50, 40, emoji = "👨‍👩‍👧"),
        LessonTopic("hobbies", "Хобби и увлечения", 60, 15, emoji = "🎨"),
        LessonTopic("weather", "Погода и природа", 55, 25, emoji = "🌤️"),
        LessonTopic("shopping", "Покупки", 65, 0, emoji = "🛍️"),
        LessonTopic("health", "Здоровье", 75, 10, emoji = "💊"),
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 15.dp)

        ) {
            items(topics) { topic ->
                LessonCategoryCard(topic)
            }
        }
    }
}

@Composable
fun LessonCategoryCard(topic: LessonTopic) {
    val progress = (topic.completedWords.toFloat() / topic.totalWords.toFloat()) * 100
    val isCompleted = topic.completedWords == topic.totalWords

    val backgroundColor = when (topic.level) {
        "A0", "A1" -> GreenPrimary
        "A2" -> BluePrimary
        "B1", "B2" -> OrangePrimary
        "C1", "C2" -> RedPrimary
        else -> GreenPrimary
    }

    CardBlock(
        modifier = Modifier
            .clickable { },
        backgroundColor = White
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Цветной квадрат с эмоджи
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .rotate(if (topic.id.hashCode() % 2 == 0) 3f else -3f)
                    .background(
                        Brush.horizontalGradient(
                            listOf(backgroundColor, backgroundColor.copy(alpha = 0.85f))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(text = topic.emoji, fontSize = 32.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Заголовок
            Text(
                text = topic.title,
                style = MaterialTheme.typography.titleSmall,
                color = TextPrimary,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Количество слов
            Text(
                text = "${topic.totalWords} слов",
                fontSize = 12.sp,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Прогресс-бар
            ProgressBar(progress)
        }
    }
}

@Preview
@Composable
fun LessonsPagePrew(){
    LessonsPage()
}
