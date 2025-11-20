package com.example.lingai.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
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
import com.example.lingai.ui.model.SeasonalTheme

//
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
        SeasonalTheme(
            2,
            "Хэллоуин",
            "https://images.unsplash.com/photo-1667223687781-b6025c7cd48d?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=400",
            18,
            35
        ),
        SeasonalTheme(
            3,
            "Новый год",
            "https://images.unsplash.com/photo-1704399527621-82de0422490c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=400",
            32,
            80
        ),
        SeasonalTheme(
            4,
            "Зима",
            "https://images.unsplash.com/photo-1542609715982-16f4e1e2cfc0?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&w=400",
            28,
            50
        )
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        contentPadding = PaddingValues(end = 20.dp),
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
//@Composable
//fun FeatureList(
//    list: List<SeasonalTheme>,
//    modifier: Modifier = Modifier,
//) {
//    val lazyListState = rememberLazyListState()
//
//    val repeatedItems = remember { list + list + list }
//    val middleIndex = list.size
//
//    // Флаг, что autoplay временно выключен
//    var isAutoScrollEnabled by remember { mutableStateOf(true) }
//
//    // Текущее время последнего скролла руками
//    var lastUserScrollTime by remember { mutableStateOf(0L) }
//
//    val coroutineScope = rememberCoroutineScope()
//
//    // === 1. Инициализация: прыжок в центр ===
//    LaunchedEffect(Unit) {
//        lazyListState.scrollToItem(middleIndex)
//    }
//
//    // === 2. Автоскролл, который уважает паузы ===
//    LaunchedEffect(isAutoScrollEnabled) {
//        while (true) {
//            if (isAutoScrollEnabled) {
//                lazyListState.scroll(MutatePriority.PreventUserInput) {
//                    scrollBy(1f)
//                }
//            }
//            delay(8)
//        }
//    }
//
//    // === 3. Следим за ручным скроллом ===
//    LaunchedEffect(lazyListState) {
//        snapshotFlow { lazyListState.isScrollInProgress }
//            .collect { isScrolling ->
//                if (isScrolling) {
//                    // пользователь начал скролл
//                    lastUserScrollTime = System.currentTimeMillis()
//                    isAutoScrollEnabled = false
//                } else {
//                    // пользователь закончил скролл — ждём 5 секунд
//                    coroutineScope.launch {
//                        delay(5000)
//                        val now = System.currentTimeMillis()
//                        if (now - lastUserScrollTime >= 5000) {
//                            isAutoScrollEnabled = true
//                        }
//                    }
//                }
//            }
//    }
//
//    // === 4. Следим за уходом к краям и прыгаем обратно ===
//    LaunchedEffect(lazyListState) {
//        snapshotFlow { lazyListState.firstVisibleItemIndex }
//            .collect { first ->
//                val total = repeatedItems.size
//                val safeZone = list.size
//
//                when {
//                    first < middleIndex - safeZone ->
//                        lazyListState.scrollToItem(first + list.size)
//
//                    first > middleIndex + safeZone ->
//                        lazyListState.scrollToItem(first - list.size)
//                }
//            }
//    }
//
//    LazyRow(
//        state = lazyListState,
//        modifier = modifier
//            .pointerInput(Unit) {
//                // блокируем автоскрол по любому тачу
//                awaitPointerEventScope {
//                    while (true) {
//                        awaitPointerEvent()
//                        lastUserScrollTime = System.currentTimeMillis()
//                        isAutoScrollEnabled = false
//                    }
//                }
//            },
//        horizontalArrangement = Arrangement.spacedBy(12.dp)
//    ) {
//        items(repeatedItems) { item ->
//            SeasonalThemeCard(item)
//        }
//    }
//}
//
//
//
//
//private tailrec suspend fun autoScroll(lazyListState: LazyListState) {
//    lazyListState.scroll(MutatePriority.PreventUserInput) {
//        scrollBy(SCROLL_DX)
//    }
//    delay(DELAY_BETWEEN_SCROLL_MS)
//
//    autoScroll(lazyListState)
//}
//
//private const val DELAY_BETWEEN_SCROLL_MS = 8L
//private const val SCROLL_DX = 1f
