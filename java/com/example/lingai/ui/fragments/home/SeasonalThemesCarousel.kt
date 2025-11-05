package com.example.lingai.ui.fragments.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.lingai.ui.components.ProgressBar
import com.example.lingai.ui.components.SeasonalTheme
import com.example.lingai.ui.theme.*

@Composable
fun SeasonalThemesCarousel() {
// Seasonal themes carousel
    val seasonalThemes = listOf(
        SeasonalTheme(
            1,
            "Осень",
            "https://images.unsplash.com/photo-1636327648402-fa5297a87efc?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=400",
            24,
            65
        ),
        SeasonalTheme(2, "Хэллоуин", "https://images.unsplash.com/photo-1667223687781-b6025c7cd48d?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=400", 18, 35),
        SeasonalTheme(3, "Новый год", "https://images.unsplash.com/photo-1704399527621-82de0422490c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=400", 32, 80),
        SeasonalTheme(4, "Зима", "https://images.unsplash.com/photo-1542609715982-16f4e1e2cfc0?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=400", 28, 50)
    )
    Text(
        text = "Сезонные темы",
        style = MaterialTheme.typography.titleLarge,
        color = TextSecondary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(bottom = 12.dp)
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        contentPadding = PaddingValues(horizontal = 20.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(seasonalThemes) { theme ->
            SeasonalThemeCard(theme = theme)
        }
    }
}
@Composable
fun SeasonalThemeCard(theme: SeasonalTheme) {
    Card(
        modifier = Modifier
            .width(350.dp)
            .height(240.dp)
            .clickable { },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box {
            Image(
                painter = rememberAsyncImagePainter(theme.imageUrl),
                contentDescription = theme.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = theme.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${theme.wordsCount} слов • ${theme.progress}%",
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.9f)
                )
                Spacer(modifier = Modifier.height(8.dp))
                ProgressBar(theme.progress.toFloat())
            }
        }
    }
}