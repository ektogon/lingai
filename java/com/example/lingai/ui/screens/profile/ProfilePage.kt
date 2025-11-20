package com.example.lingai.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.example.lingai.ui.components.CardBlock
import com.example.lingai.ui.components.ContentColumn
import com.example.lingai.ui.components.ThemeSwitcher
import com.example.lingai.ui.theme.Blue600
import com.example.lingai.ui.theme.Blue700
import com.example.lingai.ui.theme.BluePrimary
import com.example.lingai.ui.theme.Gold
import com.example.lingai.ui.theme.GoldDark
import com.example.lingai.ui.theme.GreenLight
import com.example.lingai.ui.theme.GreenPrimary
import com.example.lingai.ui.theme.OrangeLight
import com.example.lingai.ui.theme.OrangePrimary
import com.example.lingai.ui.theme.TextPrimary
import com.example.lingai.ui.theme.TextSecondary
import com.example.lingai.ui.theme.ThemeGradients

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
fun ProfilePage(
    darkTheme: Boolean,
    onThemeUpdate: () -> Unit
) {
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
            ThemeGradients.current.primaryGradient
        ),
        Achievement(
            "⚡", "Быстрый старт", "Первая тема завершена",
            Brush.horizontalGradient(listOf(GreenLight, Color(0xFFD4EDE0))),
            Brush.horizontalGradient(listOf(Blue600, Blue700))
        )
    )

    ContentColumn(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Профиль",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )

            ThemeSwitcher(
                darkTheme = darkTheme,
                size = 30.dp,
                padding = 5.dp,
                onClick = onThemeUpdate
            )
        }
        // 👤 Карточка пользователя
        ProfileCard(stats)
        Spacer(Modifier.height(20.dp))
        // 🏅 Достижения
        InfoCard(title = "Недавние достижения") {
            achievements.forEachIndexed { i, ach ->
                AchievementCard(ach)
                if (i != achievements.lastIndex) Spacer(Modifier.height(10.dp))
            }
        }
        Spacer(Modifier.height(20.dp))
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
                Text(
                    "Иван Петров",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                Text(
                    "Изучаю английский",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondary
                )
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
            .background(ThemeGradients.current.primaryGradient),
        contentAlignment = Alignment.Center
    ) {
        Text(initials, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun StatsGrid(stats: List<Stat>) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        stats.chunked(2).forEach { columnStats ->
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                columnStats.forEach { StatCard(it) }
            }
        }
    }
}

@Composable
fun StatCard(stat: Stat) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
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
    CardBlock {
        Column {
            if (title != null) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
            content()
        }
    }
}

@Preview
@Composable
fun ProfilePagePrew() {
    ProfilePage(true, { })
}
