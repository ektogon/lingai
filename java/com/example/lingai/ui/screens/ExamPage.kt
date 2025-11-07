package com.example.lingai.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingai.ui.components.AdviceCard
import com.example.lingai.ui.components.CardBlock
import com.example.lingai.ui.components.ContentColumn
import com.example.lingai.ui.components.GradientButton
import com.example.lingai.ui.components.LinguaSlider
import com.example.lingai.ui.theme.*

// 🧩 Модель сложности
data class ExamDifficulty(
    val id: String,
    val label: String,
    val color: Color
)

@Composable
fun ExamPage() {
    var mode by remember { mutableStateOf("random") }
    var questionCount by remember { mutableStateOf(50f) }
    val selectedTopics = remember { mutableStateListOf<String>() }
    val selectedDifficulties = remember { mutableStateListOf<String>() }

    val topics = listOf(
        "Базовые приветствия", "Кулинария и рецепты",
        "Программирование", "Спорт и фитнес", "Искусство и культура"
    )

    val difficulties = listOf(
        ExamDifficulty("easy", "Легкий", GreenPrimary),
        ExamDifficulty("medium", "Средний", BluePrimary),
        ExamDifficulty("hard", "Сложный", OrangePrimary),
        ExamDifficulty("expert", "Очень сложный", RedPrimary)
    )

    ContentColumn(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Экзамен",
            style = MaterialTheme.typography.headlineLarge,
            color = TextPrimary,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // 🎯 Режим выбора
        CardBlock {
            Column() {
                SectionTitle("🎯", "Выбор режима")

                listOf(
                    Triple("random", "🎲", "Случайные слова" to "Из всех изученных"),
                    Triple("topic", "📚", "По теме" to "Выбрать несколько тем"),
                    Triple(
                        "level",
                        "📊",
                        "По уровню сложности" to "Легкий, cредний, cложный, очень сложный"
                    )
                ).forEach { (key, emoji, titles) ->
                    ExamModeOption(
                        selected = mode == key,
                        emoji = emoji,
                        title = titles.first,
                        description = titles.second,
                        onClick = { mode = key }
                    )
                    Spacer(Modifier.height(12.dp))
                }

                when (mode) {
                    "topic" -> ExamSelectionList(
                        title = "Выберите темы (${selectedTopics.size} выбрано)",
                        items = topics,
                        selectedItems = selectedTopics
                    )

                    "level" -> DifficultySelection(
                        difficulties = difficulties,
                        selectedDifficulties = selectedDifficulties
                    )
                }
            }
        }
        Spacer(Modifier.padding(vertical = 10.dp))
        // 🎚 Количество вопросов
        CardBlock {
            LinguaSlider(
                label = "Количество вопросов",
                value = questionCount,
                onValueChange = { questionCount = it },
                valueRange = 25f..150f,
                accentColor = GreenPrimary,
                textColor = TextPrimary
            )
        }
        Spacer(Modifier.padding(vertical = 10.dp))
        // 🚀 Кнопка старта
        GradientButton(
            text = "Начать экзамен 🚀",
            colors = listOf(GreenPrimary, GreenDark)
        )

        Spacer(Modifier.padding(vertical = 10.dp))
        //Совет
        AdviceCard(
            text = "Совет: Начните с малого количества вопросов, чтобы проверить свои знания!"
        )
        Spacer(Modifier.padding(vertical = 10.dp))
    }
}

@Composable
fun SectionTitle(emoji: String, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 12.dp)
    ) {
        Text(text = emoji, fontSize = 20.sp, modifier = Modifier.padding(end = 8.dp))
        Text(text = text, style = MaterialTheme.typography.titleMedium, color = TextPrimary)
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
                2.dp,
                if (selected) GreenPrimary else GreenLight,
                RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(selectedColor = GreenPrimary)
        )
        Text(emoji, fontSize = 20.sp, modifier = Modifier.padding(end = 8.dp))
        Column(Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.SemiBold, color = TextPrimary)
            Text(description, fontSize = 12.sp, color = TextSecondary)
        }
    }
}

@Composable
fun ExamSelectionList(title: String, items: List<String>, selectedItems: MutableList<String>) {
    Divider(Modifier.padding(vertical = 16.dp))
    Text(
        title,
        fontSize = 14.sp,
        color = TextSecondary,
        modifier = Modifier.padding(bottom = 12.dp)
    )

    items.forEach { item ->
        val isSelected = selectedItems.contains(item)
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)
                .clip(RoundedCornerShape(12.dp))
                .border(
                    2.dp,
                    if (isSelected) GreenPrimary else GreenLight,
                    RoundedCornerShape(12.dp)
                )
                .background(if (isSelected) GreenLight else Color.Transparent)
                .clickable {
                    if (isSelected) selectedItems.remove(item) else selectedItems.add(item)
                }
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isSelected,
                onCheckedChange = {
                    if (isSelected) selectedItems.remove(item) else selectedItems.add(
                        item
                    )
                },
                colors = CheckboxDefaults.colors(checkedColor = GreenPrimary)
            )
            Spacer(Modifier.width(8.dp))
            Text(item, color = TextPrimary)
        }
    }
}

@Composable
fun DifficultySelection(
    difficulties: List<ExamDifficulty>,
    selectedDifficulties: MutableList<String>
) {
    Divider(Modifier.padding(vertical = 16.dp))
    Text(
        text = "Выберите сложность (${selectedDifficulties.size} выбрано)",
        fontSize = 14.sp,
        color = TextSecondary,
        modifier = Modifier.padding(bottom = 12.dp)
    )

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        difficulties.forEach { difficulty ->
            val isSelected = selectedDifficulties.contains(difficulty.id)
            Button(
                onClick = {
                    if (isSelected) selectedDifficulties.remove(difficulty.id)
                    else selectedDifficulties.add(difficulty.id)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) difficulty.color else White
                ),
                shape = RoundedCornerShape(12.dp),
                border = if (!isSelected)
                    BorderStroke(2.dp, GreenLight)
                else null
            ) {
                Text(
                    difficulty.label,
                    color = if (isSelected) White else TextPrimary,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
fun ExamPagePrew() {
    ExamPage()
}