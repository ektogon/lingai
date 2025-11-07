package com.example.lingai.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingai.ui.components.ContentColumn
import com.example.lingai.ui.theme.*

// 🎨 Цвета

// 📊 Модели
data class Stat(val emoji: String, val label: String, val value: String, val color: Color)
data class Achievement(
    val emoji: String,
    val title: String,
    val description: String,
    val background: Brush,
    val iconBackground: Brush
)

// 🌿 Главный экран профиля
@Composable
fun ProfilePage() {
    val stats = listOf(
        Stat("🔥", "Дней подряд", "15", Gold),
        Stat("💎", "Очков", "2,845", GreenPrimary),
        Stat("🎯", "Слов изучено", "342", BluePrimary),
        Stat("⏱️", "Минут сегодня", "45", OrangePrimary)
    )

    val achievements = listOf(
        Achievement(
            "🏆", "Первая неделя", "7 дней подряд",
            Brush.horizontalGradient(listOf(OrangeLight, Color(0xFFFFD89B))),
            Brush.horizontalGradient(listOf(Gold, GoldDark))
        ),
        Achievement(
            "📚", "Знаток слов", "300 слов изучено",
            Brush.horizontalGradient(listOf(GreenLight, GreenLight)),
            Brush.horizontalGradient(listOf(GreenPrimary, GreenDark))
        ),
        Achievement(
            "⚡", "Быстрый старт", "Первая тема завершена",
            Brush.horizontalGradient(listOf(GreenLight, Color(0xFFD4EDE0))),
            Brush.horizontalGradient(listOf(BluePrimary, BlueDark))
        )
    )

    ContentColumn(modifier = Modifier.verticalScroll(rememberScrollState())){
        // 👤 Карточка пользователя
        ProfileCard(stats)

        // 🏅 Достижения
        InfoCard(title = "Недавние достижения") {
            achievements.forEachIndexed { i, ach ->
                AchievementCard(ach)
                if (i != achievements.lastIndex) Spacer(Modifier.height(10.dp))
            }
        }
    }
}

// =================== 🔽 Компоненты ===================

@Composable
fun ProfileCard(stats: List<Stat>) {
    InfoCard {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Avatar("ИП")
            Column {
                Text("Иван Петров", style = MaterialTheme.typography.titleLarge, color = TextPrimary)
                Text("Изучаю английский", style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
            }
        }

        StatsGrid(stats)
    }
}

@Composable
fun Avatar(initials: String) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(
                Brush.horizontalGradient(
                    listOf(GreenPrimary, GreenDark)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(initials, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun StatsGrid(stats: List<Stat>) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        stats.chunked(2).forEach { columnStats ->
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                columnStats.forEach { StatCard(it) }
            }
        }
    }
}

@Composable
fun StatCard(stat: Stat) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Background),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stat.emoji, fontSize = 22.sp, modifier = Modifier.padding(bottom = 6.dp))
            Text(stat.value, fontSize = 18.sp, color = stat.color, fontWeight = FontWeight.Bold)
            Text(stat.label, fontSize = 12.sp, color = TextSecondary, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun AchievementCard(achievement: Achievement) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.background(achievement.background)) {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(achievement.iconBackground),
                    contentAlignment = Alignment.Center
                ) {
                    Text(achievement.emoji, fontSize = 16.sp, color = Color.White)
                }
                Column(Modifier.weight(1f)) {
                    Text(
                        achievement.title,
                        style = MaterialTheme.typography.titleSmall,
                        color = TextPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        achievement.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary
                    )
                }
            }
        }
    }
}

@Composable
fun InfoCard(
    title: String? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(Modifier.padding(20.dp)) {
            if (title != null) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = TextPrimary,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
            content()
        }
    }
}

@Preview
@Composable
fun ProfilePagePrew(){
    ProfilePage()
}
