package com.example.lingai.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingai.ui.theme.TextPrimary
import com.example.lingai.ui.theme.TextSecondary

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
    CardBlock(
        modifier = modifier
            .clickable { },
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .rotate(rotation)
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
            Text(text = title, style = MaterialTheme.typography.titleSmall, color = TextPrimary)
            Text(text = "$wordsCount слов", fontSize = 12.sp, color = TextSecondary)
            Spacer(modifier = Modifier.height(8.dp))
            ProgressBar(progress.toFloat())
        }
    }
}