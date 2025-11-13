package com.example.lingai.ui.screens.learn

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingai.model.Question
import com.example.lingai.ui.theme.Blue
import com.example.lingai.ui.theme.TextPrimary

@Composable
fun QuestionContent(
    question: Question,
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
        Text(
            text = question.correctAnswer.original,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 32.dp)
        )

        Spacer(Modifier.height(40.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 33.dp)
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
            Button(
                onClick = onSkip,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)
                    .padding(horizontal = 33.dp, vertical = 24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Blue),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("ПРОПУСТИТЬ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
