package com.example.lingai.ui.screens.learn.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import com.example.lingai.ui.components.ProgressBar
import com.example.lingai.ui.theme.OrangePrimary

@Composable
fun LearnHeader(
    progress: Float, current: Int, total: Int, isReviewMode: Boolean, onClose: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onClose, modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        ) {
            Icon(Icons.Default.Close, contentDescription = "Close", tint = MaterialTheme.colorScheme.onBackground)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("$current / $total", color = MaterialTheme.colorScheme.onBackground)
            if (isReviewMode) Text(
                " • Повторение",
                color = OrangePrimary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
    ProgressBar(progress)
}
