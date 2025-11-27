package com.example.lingai.ui.screens.learn.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingai.domain.models.QuestionModel
import com.example.lingai.ui.components.GradientButton

@Composable
fun QuestionContent(
    question: QuestionModel,
    selectedAnswer: Int?,
    showResult: Boolean,
    isCorrect: Boolean,
    onSelect: (Int) -> Unit,
    onSkip: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(Modifier.height(60.dp))
        Box(modifier = Modifier.height(104.dp)){
            Text(
                text = question.correctAnswer.original,
                fontSize = 50.sp,
                lineHeight = 52.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
            )
        }

        Spacer(Modifier.height(60.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Top
        ) {
            question.variants.forEachIndexed { i, variant ->
                VariantItem(
                    number = i + 1,
                    text = variant.translation,
                    isSelected = selectedAnswer == i,
                    isCorrect = i == question.correctIndex,
                    showResult = showResult,
                    onClick = { onSelect(i) }
                )
                Spacer(Modifier.height(12.dp))
            }
        }
        if (!showResult) {
            GradientButton(
                onClick = onSkip,
                text = "ПРОПУСТИТЬ",
            )
            Spacer(Modifier.height(24.dp))
        }
    }
}
