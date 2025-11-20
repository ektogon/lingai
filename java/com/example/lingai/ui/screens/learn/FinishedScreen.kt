package com.example.lingai.ui.screens.learn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import com.example.lingai.ui.theme.Background
import com.example.lingai.ui.theme.GreenLight
import com.example.lingai.ui.theme.GreenPrimary
import com.example.lingai.ui.theme.OrangePrimary
import com.example.lingai.ui.theme.TextPrimary
import com.example.lingai.ui.theme.TextSecondary
import com.example.lingai.ui.theme.White

@Composable
fun FinishedScreen(
    state: LearnWordState,
    onRestartReview: () -> Unit,
    onClose: () -> Unit
) {
    val correctCount =
        if (state.isReviewMode) state.total
        else state.total - state.wrongAnswers.size

    val hasErrors = state.wrongAnswers.isNotEmpty() && !state.isReviewMode

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {

            // Emoji
            Text(
                text = if (hasErrors) "📚" else "🎉",
                fontSize = 60.sp
            )

            Spacer(Modifier.height(16.dp))

            // Title
            Text(
                text = if (state.isReviewMode) "Повторение завершено!" else "Отлично!",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(24.dp))

            // Card with stats
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = White)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {

                    Text(
                        text = "Правильных ответов:",
                        color = TextSecondary
                    )

                    Spacer(Modifier.height(6.dp))

                    Text(
                        text = "$correctCount / ${state.total}",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = GreenPrimary
                    )

                    Spacer(Modifier.height(16.dp))

                    // Progress Bar
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(12.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(GreenLight)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(correctCount.toFloat() / state.total.toFloat())
                                .background(
                                    Brush.horizontalGradient(
                                        listOf(GreenPrimary, Color(0xFF588B5E))
                                    )
                                )
                        )
                    }

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "${((correctCount.toFloat() / state.total) * 100).toInt()}% правильно",
                        fontSize = 14.sp,
                        color = TextSecondary
                    )
                }
            }

            Spacer(Modifier.height(32.dp))

            // Buttons
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                if (hasErrors) {
                    Button(
                        onClick = onRestartReview,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Icon(Icons.Default.Close, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "Исправить ошибки (${state.wrongAnswers.size})",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Button(
                    onClick = onClose,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.horizontalGradient(
                                    listOf(GreenPrimary, Color(0xFF588B5E))
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (state.isReviewMode) "Вернуться к темам" else "Завершить",
                            color = White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
