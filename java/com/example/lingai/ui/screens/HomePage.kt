package com.example.lingai.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

data class SeasonalTheme(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val wordsCount: Int,
    val progress: Int
)

// Duolingo muted green color palette
object DuolingoColors {
    val GreenPrimary = Color(0xFF6B9E78)
    val GreenDark = Color(0xFF588B5E)
    val GreenDarker = Color(0xFF4A7354)
    val GreenLight = Color(0xFFE5F4EA)
    val OrangePrimary = Color(0xFFFF9500)
    val OrangeDark = Color(0xFFFF7A00)
    val OrangeLight = Color(0xFFFFE5B4)
    val BluePrimary = Color(0xFF4A9EED)
    val BlueDark = Color(0xFF3B7EC9)
    val Gold = Color(0xFFFFD700)
    val Background = Color(0xFFF7FDF9)
    val TextPrimary = Color(0xFF3C3C3C)
    val TextSecondary = Color(0xFF666666)
    val White = Color.White
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    val seasonalThemes = listOf(
        SeasonalTheme(1, "Осень", "https://images.unsplash.com/photo-1636327648402-fa5297a87efc?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=400", 24, 65),
        SeasonalTheme(2, "Хэллоуин", "https://images.unsplash.com/photo-1667223687781-b6025c7cd48d?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=400", 18, 35),
        SeasonalTheme(3, "Новый год", "https://images.unsplash.com/photo-1704399527621-82de0422490c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=400", 32, 80),
        SeasonalTheme(4, "Зима", "https://images.unsplash.com/photo-1542609715982-16f4e1e2cfc0?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=400", 28, 50)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DuolingoColors.Background)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 80.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Custom theme generator - Duolingo style - MOVED TO TOP
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            listOf(DuolingoColors.GreenPrimary, DuolingoColors.GreenDark)
                        )
                    )
                    .padding(20.dp)
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(bottom = 12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(DuolingoColors.White.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "✨", fontSize = 16.sp)
                        }
                        Text(
                            text = "Создай свою тему",
                            style = MaterialTheme.typography.titleLarge,
                            color = DuolingoColors.White
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = "",
                            onValueChange = {},
                            placeholder = { Text("Космос, Еда, Спорт...") },
                            modifier = Modifier
                                .weight(1f)
                                .height(56.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = DuolingoColors.White,
                                unfocusedContainerColor = DuolingoColors.White,
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent
                            )
                        )
                        Button(
                            onClick = { },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = DuolingoColors.White,
                                contentColor = DuolingoColors.GreenPrimary
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.height(56.dp),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
                        ) {
                            Text("Создать", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Daily goal progress
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = DuolingoColors.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = "🎯", fontSize = 18.sp)
                        Text(
                            text = "Дневная цель",
                            style = MaterialTheme.typography.titleMedium,
                            color = DuolingoColors.TextPrimary
                        )
                    }
                    Text(
                        text = "12/20 слов",
                        fontSize = 14.sp,
                        color = DuolingoColors.GreenPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                LinearProgressIndicator(
                    progress = { 0.6f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp)
                        .clip(RoundedCornerShape(6.dp)),
                    color = DuolingoColors.GreenPrimary,
                    trackColor = DuolingoColors.GreenLight
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Еще 8 слов до награды! 🏆",
                    fontSize = 12.sp,
                    color = DuolingoColors.TextSecondary
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Seasonal themes carousel
        Text(
            text = "Сезонные темы",
            style = MaterialTheme.typography.titleLarge,
            color = DuolingoColors.TextPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 12.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            items(seasonalThemes) { theme ->
                SeasonalThemeCard(theme = theme)
            }
        }

        // Popular categories
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Популярные темы",
                style = MaterialTheme.typography.titleLarge,
                color = DuolingoColors.TextPrimary
            )
            Icon(
                Icons.Default.Star,
                contentDescription = null,
                tint = DuolingoColors.Gold,
                modifier = Modifier.size(18.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CategoryCard(
                modifier = Modifier.weight(1f),
                emoji = "💼",
                title = "Бизнес",
                wordsCount = 120,
                progress = 40,
                backgroundColor = DuolingoColors.GreenPrimary,
                rotation = 3f
            )
            CategoryCard(
                modifier = Modifier.weight(1f),
                emoji = "✈️",
                title = "Путешествия",
                wordsCount = 95,
                progress = 65,
                backgroundColor = DuolingoColors.BluePrimary,
                rotation = -3f
            )
        }

        // Quick start
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Начни сегодня",
                style = MaterialTheme.typography.titleLarge,
                color = DuolingoColors.TextPrimary
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(DuolingoColors.OrangeLight)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = null,
                        tint = DuolingoColors.OrangePrimary,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = "+10 XP",
                        fontSize = 12.sp,
                        color = DuolingoColors.OrangePrimary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 24.dp)
                .clickable { },
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            listOf(DuolingoColors.OrangeLight, Color(0xFFFFD89B))
                        )
                    )
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                Brush.horizontalGradient(
                                    listOf(DuolingoColors.OrangePrimary, DuolingoColors.OrangeDark)
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "🎯", fontSize = 32.sp)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(bottom = 4.dp)
                        ) {
                            Text(
                                text = "Урок дня",
                                style = MaterialTheme.typography.titleMedium,
                                color = DuolingoColors.TextPrimary
                            )
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(DuolingoColors.GreenPrimary)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "НОВЫЙ",
                                    fontSize = 10.sp,
                                    color = DuolingoColors.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Text(
                            text = "Базовые фразы приветствия",
                            fontSize = 14.sp,
                            color = DuolingoColors.TextPrimary.copy(alpha = 0.8f),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(text = "⚡", fontSize = 12.sp)
                                Text(
                                    text = "5 мин",
                                    fontSize = 12.sp,
                                    color = DuolingoColors.OrangePrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(text = "🏆", fontSize = 12.sp)
                                Text(
                                    text = "+15 XP",
                                    fontSize = 12.sp,
                                    color = DuolingoColors.GreenPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }

        // Navigate button - Duolingo style with shadow
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = DuolingoColors.GreenPrimary
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 8.dp,
                pressedElevation = 4.dp
            )
        ) {
            Text(
                text = "Все мои темы",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun SeasonalThemeCard(theme: SeasonalTheme) {
    Card(
        modifier = Modifier
            .width(280.dp)
            .clickable { },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = DuolingoColors.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(3f / 2f)
                ) {
//                    Image(
//                        painter = rememberAsyncImagePainter(theme.imageUrl),
//                        contentDescription = theme.title,
//                        contentScale = ContentScale.Crop,
//                        modifier = Modifier.fillMaxSize()
//                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.6f)
                                    ),
                                    startY = 100f
                                )
                            )
                    )
                    // Progress badge
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(12.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(DuolingoColors.White)
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "${theme.progress}%",
                            fontSize = 12.sp,
                            color = DuolingoColors.GreenPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // White bottom section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DuolingoColors.White)
                        .padding(16.dp)
                ) {
                    Text(
                        text = theme.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = DuolingoColors.TextPrimary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${theme.wordsCount} слов",
                            fontSize = 12.sp,
                            color = DuolingoColors.TextSecondary
                        )
                        Text(
                            text = "${theme.progress}% завершено",
                            fontSize = 12.sp,
                            color = DuolingoColors.GreenPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    LinearProgressIndicator(
                        progress = { theme.progress / 100f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = DuolingoColors.GreenPrimary,
                        trackColor = DuolingoColors.GreenLight
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    emoji: String,
    title: String,
    wordsCount: Int,
    progress: Int,
    backgroundColor: Color,
    rotation: Float
) {
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .clickable { },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = DuolingoColors.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .rotate(rotation)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.horizontalGradient(
                            listOf(backgroundColor, backgroundColor.copy(alpha = 0.8f))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(text = emoji, fontSize = 32.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = DuolingoColors.TextPrimary,
                textAlign = TextAlign.Center
            )
            Text(
                text = "$wordsCount слов",
                fontSize = 12.sp,
                color = DuolingoColors.TextSecondary,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            LinearProgressIndicator(
                progress = { progress / 100f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = backgroundColor,
                trackColor = DuolingoColors.GreenLight
            )
        }
    }
}

@Preview
@Composable
fun HomePagePrew(){
    HomePage()
}