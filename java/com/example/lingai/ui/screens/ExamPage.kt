package com.example.lingai.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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

object DuolingoExamColors {
    val GreenPrimary = Color(0xFF6B9E78)
    val GreenDark = Color(0xFF588B5E)
    val GreenLight = Color(0xFFE5F4EA)
    val OrangePrimary = Color(0xFFFF9500)
    val BluePrimary = Color(0xFF4A9EED)
    val RedPrimary = Color(0xFFEF4444)
    val Background = Color(0xFFF7FDF9)
    val TextPrimary = Color(0xFF3C3C3C)
    val TextSecondary = Color(0xFF666666)
    val White = Color.White
}

data class ExamLevel(
    val code: String,
    val label: String,
    val color: Color
)

@Composable
fun ExamPage() {
    var mode by remember { mutableStateOf("random") }
    var questionCount by remember { mutableStateOf(50f) }
    val selectedTopics = remember { mutableStateListOf<String>() }
    val selectedLevels = remember { mutableStateListOf<String>() }

    val topics = listOf(
        "Все слова A0",
        "Все слова A1",
        "Все слова A2",
        "Базовые приветствия",
        "Кулинария и рецепты",
        "Программирование",
        "Спорт и фитнес"
    )

    val levels = listOf(
        ExamLevel("A0", "A0", DuolingoExamColors.GreenPrimary),
        ExamLevel("A1", "A1", DuolingoExamColors.GreenPrimary),
        ExamLevel("A2", "A2", DuolingoExamColors.BluePrimary),
        ExamLevel("B1", "B1", DuolingoExamColors.OrangePrimary),
        ExamLevel("B2", "B2", DuolingoExamColors.OrangePrimary),
        ExamLevel("C1", "C1", DuolingoExamColors.RedPrimary),
        ExamLevel("C2", "C2", DuolingoExamColors.RedPrimary)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DuolingoExamColors.Background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .padding(bottom = 64.dp)
    ) {
        Text(
            text = "Экзамен",
            style = MaterialTheme.typography.headlineLarge,
            color = DuolingoExamColors.TextPrimary,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // Mode selection
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = DuolingoExamColors.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Text(text = "🎯", fontSize = 20.sp, modifier = Modifier.padding(end = 8.dp))
                    Text(
                        text = "Выбор режима",
                        style = MaterialTheme.typography.titleMedium,
                        color = DuolingoExamColors.TextPrimary
                    )
                }

                // Random mode
                ExamModeOption(
                    selected = mode == "random",
                    emoji = "🎲",
                    title = "Случайные слова",
                    description = "Из всех изученных",
                    onClick = { mode = "random" }
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Topic mode
                ExamModeOption(
                    selected = mode == "topic",
                    emoji = "📚",
                    title = "По теме",
                    description = "Выбрать несколько тем",
                    onClick = { mode = "topic" }
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Level mode
                ExamModeOption(
                    selected = mode == "level",
                    emoji = "📊",
                    title = "По уровню сложности",
                    description = "A0, A1, A2, B1, B2, C1, C2",
                    onClick = { mode = "level" }
                )

                // Topic selection
                if (mode == "topic") {
                    Divider(modifier = Modifier.padding(vertical = 16.dp))
                    Text(
                        text = "Выберите темы (${selectedTopics.size} выбрано)",
                        fontSize = 14.sp,
                        color = DuolingoExamColors.TextSecondary,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    topics.forEach { topic ->
                        val isSelected = selectedTopics.contains(topic)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .border(
                                    width = 2.dp,
                                    color = if (isSelected) DuolingoExamColors.GreenPrimary else DuolingoExamColors.GreenLight,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .background(if (isSelected) DuolingoExamColors.GreenLight else Color.Transparent)
                                .clickable {
                                    if (isSelected) selectedTopics.remove(topic)
                                    else selectedTopics.add(topic)
                                }
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = isSelected,
                                onCheckedChange = {
                                    if (isSelected) selectedTopics.remove(topic)
                                    else selectedTopics.add(topic)
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = DuolingoExamColors.GreenPrimary
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = topic, color = DuolingoExamColors.TextPrimary)
                        }
                    }
                }

                // Level selection
                if (mode == "level") {
                    Divider(modifier = Modifier.padding(vertical = 16.dp))
                    Text(
                        text = "Выберите уровни (${selectedLevels.size} выбрано)",
                        fontSize = 14.sp,
                        color = DuolingoExamColors.TextSecondary,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        levels.chunked(4).forEach { row ->
                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                row.forEach { level ->
                                    val isSelected = selectedLevels.contains(level.code)
                                    Button(
                                        onClick = {
                                            if (isSelected) selectedLevels.remove(level.code)
                                            else selectedLevels.add(level.code)
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(48.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = if (isSelected) level.color else DuolingoExamColors.White
                                        ),
                                        shape = RoundedCornerShape(12.dp),
                                        border = if (!isSelected) BorderStroke(2.dp, DuolingoExamColors.GreenLight) else null
                                    ) {
                                        Text(
                                            text = level.label,
                                            color = if (isSelected) DuolingoExamColors.White else DuolingoExamColors.TextPrimary,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // Legend
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        LevelLegendItem("Легкий", DuolingoExamColors.GreenPrimary)
                        LevelLegendItem("Средний", DuolingoExamColors.BluePrimary)
                        LevelLegendItem("Сложный", DuolingoExamColors.OrangePrimary)
                        LevelLegendItem("Очень сложный", DuolingoExamColors.RedPrimary)
                    }
                }
            }
        }

        // Question count
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = DuolingoExamColors.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Количество вопросов",
                        style = MaterialTheme.typography.titleMedium,
                        color = DuolingoExamColors.TextPrimary
                    )
                    Text(
                        text = questionCount.toInt().toString(),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = DuolingoExamColors.GreenPrimary
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Slider(
                    value = questionCount,
                    onValueChange = { questionCount = it },
                    valueRange = 25f..150f,
                    steps = 24,
                    colors = SliderDefaults.colors(
                        thumbColor = DuolingoExamColors.GreenPrimary,
                        activeTrackColor = DuolingoExamColors.GreenPrimary
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "25", fontSize = 12.sp, color = DuolingoExamColors.TextSecondary)
                    Text(text = "150", fontSize = 12.sp, color = DuolingoExamColors.TextSecondary)
                }
            }
        }

        // Start button
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(0.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.horizontalGradient(
                            listOf(DuolingoExamColors.GreenPrimary, DuolingoExamColors.GreenDark)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Начать экзамен 🚀",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DuolingoExamColors.White
                )
            }
        }

        // Info card
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = DuolingoExamColors.GreenLight
            )
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.Top
            ) {
                Text(text = "💡", fontSize = 24.sp, modifier = Modifier.padding(end = 12.dp))
                Text(
                    text = "Совет: Начните с меньшего количества вопросов, чтобы проверить свои знания!",
                    fontSize = 14.sp,
                    color = DuolingoExamColors.TextPrimary,
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Composable
fun ExamModeOption(
    selected: Boolean,
    emoji: String,
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 2.dp,
                color = if (selected) DuolingoExamColors.GreenPrimary else DuolingoExamColors.GreenLight,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = DuolingoExamColors.GreenPrimary
            )
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = emoji, fontSize = 20.sp, modifier = Modifier.padding(end = 8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                color = DuolingoExamColors.TextPrimary
            )
            Text(
                text = description,
                fontSize = 12.sp,
                color = DuolingoExamColors.TextSecondary
            )
        }
    }
}

@Composable
fun LevelLegendItem(label: String, color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(color)
        )
        Text(
            text = label,
            fontSize = 11.sp,
            color = DuolingoExamColors.TextSecondary
        )
    }
}