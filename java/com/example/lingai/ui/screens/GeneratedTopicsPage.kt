package com.example.lingai.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingai.ui.components.CardBlock
import com.example.lingai.ui.components.LinguaSlider
import com.example.lingai.ui.components.ProgressBar
import com.example.lingai.ui.components.TopicTextField
import com.example.lingai.ui.theme.*

data class Topic(
    val id: Int,
    val title: String,
    val totalWords: Int,
    val completedWords: Int,
    val difficulties: List<String>,
    val difficultyColors: List<Color>
)

data class DifficultyOption(
    val id: String,
    val label: String,
    val color: Color
)

@Composable
fun GeneratedTopicsPage() {
    var wordCount by remember { mutableStateOf(50f) }
    val selectedDifficulties = remember { mutableStateListOf("medium") }

    val difficultyOptions = listOf(
        DifficultyOption("easy", "Легкий", GreenPrimary),
        DifficultyOption("medium", "Средний", BluePrimary),
        DifficultyOption("hard", "Сложный", OrangePrimary),
        DifficultyOption("expert", "Очень сложный", RedPrimary)
    )

    val topics = listOf(
        Topic(1, "Кулинария и рецепты", 50, 35, listOf("Средний"), listOf(OrangePrimary)),
        Topic(
            2,
            "Программирование",
            80,
            12,
            listOf("Сложный", "Очень сложный"),
            listOf(OrangePrimary, RedPrimary)
        ),
        Topic(3, "Спорт и фитнес", 40, 40, listOf("Легкий"), listOf(GreenPrimary)),
        Topic(
            4,
            "Искусство и культура",
            60,
            18,
            listOf("Легкий", "Средний"),
            listOf(GreenPrimary, BluePrimary)
        ),
        Topic(5, "Медицина и здоровье", 70, 0, listOf("Сложный"), listOf(RedPrimary)),
        Topic(
            6,
            "Животные и природа",
            45,
            28,
            listOf("Легкий", "Средний"),
            listOf(GreenPrimary, BluePrimary)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .padding(bottom = 64.dp)
    ) {
        // ✨ Форма генерации темы
        CardBlock(
            gradient = Brush.linearGradient(
                listOf(GreenPrimary, GreenDark)
            ),
            backgroundColor = Color.Transparent,
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Text(
                        "✨",
                        fontSize = 22.sp,
                        color = Color.White,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        "Создай свою тему",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                TopicTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                // Слайдер количества слов
                LinguaSlider(
                    label = "Количество слов",
                    value = wordCount,
                    valueRange = 25f..150f,
                    onValueChange = { wordCount = it },
                    accentColor = Color.White,
                    textColor = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Сложность
                Text(
                    "Сложность (${selectedDifficulties.size} выбрано)",
                    color = Color.White,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                difficultyOptions.forEach { option ->
                    val isSelected = selectedDifficulties.contains(option.id)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                width = 2.dp,
                                color = if (isSelected) Color.White else Color.White.copy(alpha = 0.3f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .background(
                                if (isSelected) Color.White.copy(alpha = 0.2f)
                                else Color.White.copy(alpha = 0.1f)
                            )
                            .clickable {
                                if (isSelected) selectedDifficulties.remove(option.id)
                                else selectedDifficulties.add(option.id)
                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .clip(CircleShape)
                                .background(option.color)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(option.label, color = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Кнопка генерации
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Сгенерировать тему ✨", color = GreenPrimary, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 📋 Список созданных тем
        Text(
            "Созданные темы",
            fontSize = 18.sp,
            color = TextPrimary,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(12.dp))

        topics.forEach { topic ->
            GeneratedTopicCard(topic)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
private fun GeneratedTopicCard(topic: Topic) {
    val progress = topic.completedWords.toFloat() / topic.totalWords.toFloat()
    val isCompleted = topic.completedWords == topic.totalWords

    CardBlock(
        backgroundColor = White,
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    topic.title,
                    color = TextPrimary,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )
                if (isCompleted) {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = GreenPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Бейджи сложностей
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                topic.difficulties.forEachIndexed { i, diff ->
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(topic.difficultyColors[i])
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            diff,
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(GreenLight)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        "${topic.totalWords} слов",
                        color = GreenPrimary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Прогресс: ${topic.completedWords} / ${topic.totalWords}",
                    color = TextSecondary,
                    fontSize = 13.sp
                )
                Text(
                    "${(progress * 100).toInt()}%",
                    color = GreenPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            ProgressBar(value = progress * 100, modifier = Modifier.height(8.dp))
        }
    }
}

@Preview
@Composable
fun GeneratedTopicsPagePrew() {
    GeneratedTopicsPage()
}
