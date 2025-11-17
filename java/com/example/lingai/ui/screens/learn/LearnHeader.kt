package com.example.lingai.ui.screens.learn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lingai.ui.theme.Neutral
import com.example.lingai.ui.theme.NeutralDark
import com.example.lingai.ui.theme.Orange
import com.example.lingai.ui.theme.TextPrimary
import com.example.lingai.ui.theme.TextSecondary

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
            .fillMaxWidth(),
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
