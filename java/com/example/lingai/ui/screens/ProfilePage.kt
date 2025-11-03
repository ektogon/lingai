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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Stat(
    val emoji: String,
    val label: String,
    val value: String,
    val color: Color
)

data class Achievement(
    val emoji: String,
    val title: String,
    val description: String,
    val backgroundColor: Brush,
    val iconBackground: Brush
)

object DuolingoProfileColors {
    val GreenPrimary = Color(0xFF6B9E78)
    val GreenDark = Color(0xFF588B5E)
    val GreenLight = Color(0xFFE5F4EA)
    val OrangePrimary = Color(0xFFFF9500)
    val OrangeLight = Color(0xFFFFE5B4)
    val BluePrimary = Color(0xFF4A9EED)
    val BlueDark = Color(0xFF3B7EC9)
    val Gold = Color(0xFFFFD700)
    val GoldDark = Color(0xFFFFB700)
    val Background = Color(0xFFF7FDF9)
    val TextPrimary = Color(0xFF3C3C3C)
    val TextSecondary = Color(0xFF666666)
    val White = Color.White
}

@Composable
fun ProfilePage() {
    val stats = listOf(
        Stat("🏆", "Дней подряд", "15", DuolingoProfileColors.Gold),
        Stat("🏆", "Очков", "2,845", DuolingoProfileColors.GreenPrimary),
        Stat("🎯", "Слов изучено", "342", DuolingoProfileColors.BluePrimary),
        Stat("⏱️", "Минут сегодня", "45", DuolingoProfileColors.OrangePrimary)
    )

    val achievements = listOf(
        Achievement(
            "🏆",
            "Первая неделя",
            "7 дней подряд",
            Brush.horizontalGradient(listOf(DuolingoProfileColors.OrangeLight, Color(0xFFFFD89B))),
            Brush.horizontalGradient(listOf(DuolingoProfileColors.Gold, DuolingoProfileColors.GoldDark))
        ),
        Achievement(
            "📚",
            "Знаток слов",
            "300 слов изучено",
            Brush.horizontalGradient(listOf(DuolingoProfileColors.GreenLight, DuolingoProfileColors.GreenLight)),
            Brush.horizontalGradient(listOf(DuolingoProfileColors.GreenPrimary, DuolingoProfileColors.GreenDark))
        ),
        Achievement(
            "⚡",
            "Быстрый старт",
            "Первая тема завершена",
            Brush.horizontalGradient(listOf(DuolingoProfileColors.GreenLight, Color(0xFFD4EDE0))),
            Brush.horizontalGradient(listOf(DuolingoProfileColors.BluePrimary, DuolingoProfileColors.BlueDark))
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DuolingoProfileColors.Background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .padding(bottom = 64.dp)
    ) {
        Text(
            text = "Профиль",
            style = MaterialTheme.typography.headlineLarge,
            color = DuolingoProfileColors.TextPrimary,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // User info card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = DuolingoProfileColors.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.horizontalGradient(
                                    listOf(DuolingoProfileColors.GreenPrimary, DuolingoProfileColors.GreenDark)
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "ИП",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column {
                        Text(
                            text = "Иван Петров",
                            style = MaterialTheme.typography.titleLarge,
                            color = DuolingoProfileColors.TextPrimary
                        )
                        Text(
                            text = "Изучаю английский",
                            style = MaterialTheme.typography.bodyMedium,
                            color = DuolingoProfileColors.TextSecondary
                        )
                    }
                }

                // Stats grid
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    stats.chunked(2).forEach { row ->
                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            row.forEach { stat ->
                                StatCard(stat)
                            }
                        }
                    }
                }
            }
        }

        // Achievements
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = DuolingoProfileColors.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Недавние достижения",
                    style = MaterialTheme.typography.titleLarge,
                    color = DuolingoProfileColors.TextPrimary,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                achievements.forEach { achievement ->
                    AchievementCard(achievement)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@Composable
fun StatCard(stat: Stat) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = DuolingoProfileColors.Background
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stat.emoji,
                fontSize = 22.sp,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Text(
                text = stat.value,
                style = MaterialTheme.typography.titleMedium,
                color = stat.color,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stat.label,
                style = MaterialTheme.typography.bodySmall,
                color = DuolingoProfileColors.TextSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun AchievementCard(achievement: Achievement) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(achievement.backgroundColor)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
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
                    Text(
                        text = achievement.emoji,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = achievement.title,
                        style = MaterialTheme.typography.titleSmall,
                        color = DuolingoProfileColors.TextPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = achievement.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = DuolingoProfileColors.TextSecondary
                    )
                }
            }
        }
    }
}
