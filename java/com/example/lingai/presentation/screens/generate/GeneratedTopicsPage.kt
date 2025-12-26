package com.example.lingai.presentation.screens.generate

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingai.presentation.components.CardBlock
import com.example.lingai.presentation.components.ContentColumn
import com.example.lingai.presentation.components.LinguaSlider
import com.example.lingai.presentation.components.ProgressBar
import com.example.lingai.presentation.components.TopicTextField
import com.example.lingai.presentation.theme.BluePrimary
import com.example.lingai.presentation.theme.GreenPrimary
import com.example.lingai.presentation.theme.OrangePrimary
import com.example.lingai.presentation.theme.RedPrimary
import com.example.lingai.presentation.theme.ThemeGradients

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

    ContentColumn(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        // ✨ Форма генерации темы
        CardBlock(
            gradient = ThemeGradients.current.primaryGradient,
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
                    Text("Сгенерировать тему ✨", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 📋 Список созданных тем
        Text(
            "Созданные темы",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground,
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
        backgroundColor = MaterialTheme.colorScheme.secondary,
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    topic.title,
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )
                if (isCompleted) {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
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
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        "${topic.totalWords} слов",
                        color = MaterialTheme.colorScheme.primary,
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
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 13.sp
                )
                Text(
                    "${(progress * 100).toInt()}%",
                    color = MaterialTheme.colorScheme.primary,
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
