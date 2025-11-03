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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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

object DuolingoGenerateColors {
    val GreenPrimary = Color(0xFF6B9E78)
    val GreenDark = Color(0xFF588B5E)
    val GreenLight = Color(0xFFE5F4EA)
    val BluePrimary = Color(0xFF4A9EED)
    val OrangePrimary = Color(0xFFFF9500)
    val RedPrimary = Color(0xFFEF4444)
    val Background = Color(0xFFF7FDF9)
    val TextPrimary = Color(0xFF3C3C3C)
    val TextSecondary = Color(0xFF666666)
    val White = Color.White
}

@Composable
fun GeneratedTopicsPage() {
    var wordCount by remember { mutableStateOf(50f) }
    var topicName by remember { mutableStateOf("") }
    val selectedDifficulties = remember { mutableStateListOf("medium") }

    val difficultyOptions = listOf(
        DifficultyOption("easy", "Легкий", DuolingoGenerateColors.GreenPrimary),
        DifficultyOption("medium", "Средний", DuolingoGenerateColors.BluePrimary),
        DifficultyOption("hard", "Сложный", DuolingoGenerateColors.OrangePrimary),
        DifficultyOption("expert", "Очень сложный", DuolingoGenerateColors.RedPrimary)
    )

    val topics = listOf(
        Topic(1, "Кулинария и рецепты", 50, 35, listOf("Средний"), listOf(DuolingoGenerateColors.OrangePrimary)),
        Topic(2, "Программирование", 80, 12, listOf("Сложный", "Очень сложный"), listOf(DuolingoGenerateColors.OrangePrimary, DuolingoGenerateColors.RedPrimary)),
        Topic(3, "Спорт и фитнес", 40, 40, listOf("Легкий"), listOf(DuolingoGenerateColors.GreenPrimary)),
        Topic(4, "Искусство и культура", 60, 18, listOf("Легкий", "Средний"), listOf(DuolingoGenerateColors.GreenPrimary, DuolingoGenerateColors.OrangePrimary)),
        Topic(5, "Медицина и здоровье", 70, 0, listOf("Сложный"), listOf(DuolingoGenerateColors.RedPrimary)),
        Topic(6, "Животные и природа", 45, 28, listOf("Легкий", "Средний"), listOf(DuolingoGenerateColors.GreenPrimary, DuolingoGenerateColors.BluePrimary))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DuolingoGenerateColors.Background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .padding(bottom = 64.dp)
    ) {
        // Generator form
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            listOf(DuolingoGenerateColors.GreenPrimary, DuolingoGenerateColors.GreenDark)
                        )
                    )
                    .padding(20.dp)
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        Text(text = "✨", fontSize = 24.sp, modifier = Modifier.padding(end = 8.dp))
                        Text(
                            text = "Создай свою тему",
                            style = MaterialTheme.typography.titleLarge,
                            color = DuolingoGenerateColors.White
                        )
                    }

                    // Topic name
                    Text(
                        text = "Название темы",
                        fontSize = 14.sp,
                        color = DuolingoGenerateColors.White,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    OutlinedTextField(
                        value = topicName,
                        onValueChange = { topicName = it },
                        placeholder = { Text("Например: Морские животные") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = DuolingoGenerateColors.White.copy(alpha = 0.9f),
                            unfocusedContainerColor = DuolingoGenerateColors.White.copy(alpha = 0.9f),
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )

                    // Word count
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Количество слов",
                            fontSize = 14.sp,
                            color = DuolingoGenerateColors.White
                        )
                        Text(
                            text = wordCount.toInt().toString(),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = DuolingoGenerateColors.White
                        )
                    }
                    Slider(
                        value = wordCount,
                        onValueChange = { wordCount = it },
                        valueRange = 25f..150f,
                        steps = 24,
                        colors = SliderDefaults.colors(
                            thumbColor = DuolingoGenerateColors.White,
                            activeTrackColor = DuolingoGenerateColors.White,
                            inactiveTrackColor = DuolingoGenerateColors.White.copy(alpha = 0.3f)
                        )
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "25",
                            fontSize = 12.sp,
                            color = DuolingoGenerateColors.White.copy(alpha = 0.8f)
                        )
                        Text(
                            text = "150",
                            fontSize = 12.sp,
                            color = DuolingoGenerateColors.White.copy(alpha = 0.8f)
                        )
                    }

                    // Multiple difficulty selection
                    Text(
                        text = "Сложность (${selectedDifficulties.size} выбрано)",
                        fontSize = 14.sp,
                        color = DuolingoGenerateColors.White,
                        modifier = Modifier.padding(bottom = 12.dp)
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
                                    color = if (isSelected) DuolingoGenerateColors.White else DuolingoGenerateColors.White.copy(alpha = 0.3f),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .background(
                                    if (isSelected) DuolingoGenerateColors.White.copy(alpha = 0.2f)
                                    else Color.Transparent
                                )
                                .clickable {
                                    if (isSelected) selectedDifficulties.remove(option.id)
                                    else selectedDifficulties.add(option.id)
                                }
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = isSelected,
                                onCheckedChange = {
                                    if (isSelected) selectedDifficulties.remove(option.id)
                                    else selectedDifficulties.add(option.id)
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = DuolingoGenerateColors.White,
                                    checkmarkColor = DuolingoGenerateColors.GreenPrimary
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .clip(CircleShape)
                                    .background(option.color)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = option.label,
                                color = DuolingoGenerateColors.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Generate button
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = DuolingoGenerateColors.White
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Сгенерировать тему ✨",
                            color = DuolingoGenerateColors.GreenPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // Created topics
        Text(
            text = "Созданные темы",
            style = MaterialTheme.typography.titleMedium,
            color = DuolingoGenerateColors.TextPrimary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        topics.forEach { topic ->
            TopicCard(topic)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun TopicCard(topic: Topic) {
    val progress = (topic.completedWords.toFloat() / topic.totalWords.toFloat())
    val isCompleted = topic.completedWords == topic.totalWords

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = DuolingoGenerateColors.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Text(
                            text = topic.title,
                            style = MaterialTheme.typography.titleMedium,
                            color = DuolingoGenerateColors.TextPrimary,
                            modifier = Modifier.weight(1f)
                        )
                        if (isCompleted) {
                            Icon(
                                Icons.Default.CheckCircle,
                                contentDescription = "Completed",
                                tint = DuolingoGenerateColors.GreenPrimary,
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(start = 8.dp)
                            )
                        }
                    }

                    // Multiple difficulty badges
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.padding(bottom = 4.dp)
                    ) {
                        topic.difficulties.forEachIndexed { index, difficulty ->
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(topic.difficultyColors[index])
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = difficulty,
                                    fontSize = 11.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(DuolingoGenerateColors.GreenLight)
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "${topic.totalWords} слов",
                                fontSize = 11.sp,
                                color = DuolingoGenerateColors.GreenPrimary,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Прогресс: ${topic.completedWords} / ${topic.totalWords}",
                    style = MaterialTheme.typography.bodySmall,
                    color = DuolingoGenerateColors.TextSecondary
                )
                Text(
                    text = "${(progress * 100).toInt()}%",
                    style = MaterialTheme.typography.bodySmall,
                    color = DuolingoGenerateColors.GreenPrimary,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(DuolingoGenerateColors.GreenLight)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(progress)
                        .background(
                            Brush.horizontalGradient(
                                listOf(DuolingoGenerateColors.GreenPrimary, DuolingoGenerateColors.GreenDark)
                            )
                        )
                )
            }
        }
    }
}