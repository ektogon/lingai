package com.example.lingai.ui.screens.learn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lingai.ui.theme.*

@Composable
fun LearnHeader(
    progress: Float,
    current: Int,
    total: Int,
    isReviewMode: Boolean,
    onClose: () -> Unit
) {
    // Progress bar
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .background(Neutral)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(progress)
                .background(Brush.horizontalGradient(listOf(NeutralDark, Color(0xFF588B5E))))
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onClose,
            modifier = Modifier.size(48.dp).clip(CircleShape)
        ) {
            Icon(Icons.Default.Close, contentDescription = "Close", tint = TextPrimary)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("$current / $total", color = TextSecondary)
            if (isReviewMode)
                Text(" • Повторение", color = Orange, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
