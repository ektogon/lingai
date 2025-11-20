package com.example.lingai.ui.screens.exam

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import com.example.lingai.ui.components.AdviceCard
import com.example.lingai.ui.components.CardBlock
import com.example.lingai.ui.components.ContentColumn
import com.example.lingai.ui.components.GradientButton
import com.example.lingai.ui.components.LinguaSlider
import com.example.lingai.ui.theme.BluePrimary
import com.example.lingai.ui.theme.GreenPrimary
import com.example.lingai.ui.theme.OrangePrimary
import com.example.lingai.ui.theme.RedPrimary
import com.example.lingai.ui.theme.White

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
        ExamDifficulty("A1", "A1", GreenPrimary),
        ExamDifficulty("A2", "A2", BluePrimary),
        ExamDifficulty("B1", "B1", OrangePrimary),
        ExamDifficulty("B2", "B2", RedPrimary),
        ExamDifficulty("C1", "C1", RedPrimary),
    )

    ContentColumn(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Экзамен",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground,
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
                accentColor = MaterialTheme.colorScheme.primary,
                textColor = MaterialTheme.colorScheme.onSecondary
            )
        }
        Spacer(Modifier.padding(vertical = 10.dp))
        // 🚀 Кнопка старта
        GradientButton(
            text = "Начать экзамен 🚀",
            onClick = { }
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
        Text(text = text, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSecondary)
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
                if (selected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.secondaryContainer,
                RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
        )
        Text(emoji, fontSize = 20.sp, modifier = Modifier.padding(end = 8.dp))
        Column(Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.onSecondary)
            Text(description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSecondary)
        }
    }
}

@Composable
fun ExamSelectionList(title: String, items: List<String>, selectedItems: MutableList<String>) {
    Divider(Modifier.padding(vertical = 16.dp))
    Text(
        title,
        fontSize = 14.sp,
        color = MaterialTheme.colorScheme.onSecondary,
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
                    if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondaryContainer,
                    RoundedCornerShape(12.dp)
                )
                .background(if (isSelected) MaterialTheme.colorScheme.secondaryContainer else Color.Transparent)
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
                colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary)
            )
            Spacer(Modifier.width(8.dp))
            Text(item, color = MaterialTheme.colorScheme.onSecondary,)
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
        color = MaterialTheme.colorScheme.onSecondary,
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
                    containerColor = if (isSelected) difficulty.color else MaterialTheme.colorScheme.secondary
                ),
                shape = RoundedCornerShape(12.dp),
                border = if (!isSelected)
                    BorderStroke(2.dp, MaterialTheme.colorScheme.secondaryContainer)
                else null
            ) {
                Text(
                    difficulty.label,
                    color = if (isSelected) White else MaterialTheme.colorScheme.onSecondary,
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