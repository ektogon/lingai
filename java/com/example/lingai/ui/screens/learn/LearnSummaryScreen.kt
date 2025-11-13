package com.example.lingai.ui.screens.learn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingai.model.WrongAnswer
import com.example.lingai.ui.theme.*

@Composable
fun LearnSummaryScreen(
    wrongAnswers: List<WrongAnswer>,
    isReviewMode: Boolean,
    totalCount: Int,
    onRetry: () -> Unit,
    onFinish: () -> Unit
) {
    val correctCount = if (isReviewMode) totalCount else totalCount - wrongAnswers.size
    val hasErrors = wrongAnswers.isNotEmpty() && !isReviewMode

    Box(
        modifier = Modifier.fillMaxSize().background(Background),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(20.dp)) {
            Text(if (hasErrors) "📚" else "🎉", fontSize = 60.sp)
            Text(
                text = if (isReviewMode) "Повторение завершено!" else "Отлично!",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Spacer(Modifier.height(24.dp))
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = White),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
            ) {
                Column(Modifier.padding(24.dp)) {
                    Text("Правильных ответов: $correctCount / $totalCount", color = TextSecondary)
                    Spacer(Modifier.height(12.dp))
                    LinearProgressIndicator(
                        progress = correctCount.toFloat() / totalCount.toFloat(),
                        modifier = Modifier.fillMaxWidth().height(10.dp).clip(RoundedCornerShape(6.dp)),
                        color = NeutralDark
                    )
                    Spacer(Modifier.height(8.dp))
                    Text("${((correctCount.toFloat() / totalCount) * 100).toInt()}% правильно", color = TextSecondary)
                }
            }

            Spacer(Modifier.height(32.dp))
            Column(Modifier.fillMaxWidth().padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                if (hasErrors) {
                    Button(
                        onClick = onRetry,
                        colors = ButtonDefaults.buttonColors(containerColor = Orange),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Icon(Icons.Default.Close, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Исправить ошибки (${wrongAnswers.size})", fontSize = 16.sp)
                    }
                }

                Button(
                    onClick = onFinish,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(
                                Brush.horizontalGradient(listOf(NeutralDark, Color(0xFF588B5E)))
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (isReviewMode) "Вернуться к темам" else "Завершить",
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
